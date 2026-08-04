package com.rbc.iso20022.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.rbc.iso20022.entity.PacsMessageEntity;
import com.rbc.iso20022.exception.DuplicateMessageException;
import com.rbc.iso20022.model.CreditTransferTransaction64;
import com.rbc.iso20022.model.Document;
import com.rbc.iso20022.parser.Pacs008Parser;
import com.rbc.iso20022.repository.PacsMessageRepository;

@Service
public class Pacs008Service {

    private final PacsMessageRepository repository;
    private final CustomerService customerService;
    private final AuditService auditService;
    
    public Pacs008Service(
            PacsMessageRepository repository,
            CustomerService customerService,
            AuditService auditService) {

        this.repository = repository;
        this.customerService = customerService;
        this.auditService = auditService;
    }

    public String process(
            String xml,
            String correlationId) {

        Document document =
                Pacs008Parser.parse(xml);

        String messageId =
                extractMessageId(document);

        if (repository.existsByMessageId(messageId)) {
        	
        	auditService.audit(
        	        correlationId,
        	        null,
        	        messageId,
        	        "DUPLICATE_MESSAGE",
        	        "Duplicate PACS.008 MessageId detected",
        	        "FAILED",
        	        null);

            throw new DuplicateMessageException(
                    "Duplicate PACS.008 MessageId: "
                            + messageId);
        }

        var payment =
                document.getFIToFICstmrCdtTrf();

        var tx =
                payment.getCdtTrfTxInf().get(0);

        validateBusinessRules(tx);

        PacsMessageEntity entity =
                new PacsMessageEntity();

        if (tx.getDbtrAcct() != null
                && tx.getDbtrAcct().getId() != null
                && tx.getDbtrAcct().getId().getOthr() != null) {

            entity.setDebtorAccount(
                    tx.getDbtrAcct()
                            .getId()
                            .getOthr()
                            .getId());
        }

        if (tx.getCdtrAcct() != null
                && tx.getCdtrAcct().getId() != null
                && tx.getCdtrAcct().getId().getOthr() != null) {

            entity.setCreditorAccount(
                    tx.getCdtrAcct()
                            .getId()
                            .getOthr()
                            .getId());
        }

        if (tx.getDbtrAgt() != null
                && tx.getDbtrAgt().getFinInstnId() != null) {

            entity.setDebtorBic(
                    tx.getDbtrAgt()
                            .getFinInstnId()
                            .getBICFI());
        }

        if (tx.getCdtrAgt() != null
                && tx.getCdtrAgt().getFinInstnId() != null) {

            entity.setCreditorBic(
                    tx.getCdtrAgt()
                            .getFinInstnId()
                            .getBICFI());
        }

        entity.setMessageId(messageId);

        entity.setTxId(
                tx.getPmtId()
                        .getTxId());

        entity.setEndToEndId(
                tx.getPmtId()
                        .getEndToEndId());

        entity.setDebtorName(
                tx.getDbtr()
                        .getNm());

        entity.setCreditorName(
                tx.getCdtr()
                        .getNm());

        entity.setCurrency(
                tx.getIntrBkSttlmAmt()
                        .getCurrency());

        entity.setAmount(
                tx.getIntrBkSttlmAmt()
                        .getValue());

        entity.setCorrelationId(
                correlationId);

        entity.setStatus("PROCESSED");

        entity.setReceivedTimestamp(
                LocalDateTime.now());

        entity.setOriginalXml(xml);

        repository.save(entity);
        
        auditService.audit(
                correlationId,
                null,
                messageId,
                "PAYMENT_SAVED",
                "Payment persisted successfully",
                "SUCCESS",
                null);

        /*
         * Create or Update Debtor Customer
         */
        customerService.createOrUpdateCustomer(
                entity.getDebtorName(),
                entity.getDebtorAccount(),
                entity.getDebtorBic(),
                "SENDER");

        /*
         * Create or Update Creditor Customer
         */
        customerService.createOrUpdateCustomer(
                entity.getCreditorName(),
                entity.getCreditorAccount(),
                entity.getCreditorBic(),
                "RECEIVER");

        return "SUCCESS";
    }

    public String getMessageId(String xml) {

        Document document =
                Pacs008Parser.parse(xml);

        return extractMessageId(document);
    }

    private String extractMessageId(
            Document document) {

        if (document == null) {
            throw new IllegalArgumentException(
                    "Document is null");
        }

        if (document.getFIToFICstmrCdtTrf() == null) {
            throw new IllegalArgumentException(
                    "FIToFICstmrCdtTrf missing");
        }

        if (document.getFIToFICstmrCdtTrf()
                .getGrpHdr() == null) {

            throw new IllegalArgumentException(
                    "GrpHdr missing");
        }

        String messageId =
                document.getFIToFICstmrCdtTrf()
                        .getGrpHdr()
                        .getMsgId();

        if (messageId == null
                || messageId.isBlank()) {

            throw new IllegalArgumentException(
                    "MsgId missing");
        }

        return messageId;
    }

    private void validateBusinessRules(
            CreditTransferTransaction64 tx) {

        String senderBic = null;
        String receiverBic = null;

        if (tx.getDbtrAgt() != null
                && tx.getDbtrAgt().getFinInstnId() != null) {

            senderBic =
                    tx.getDbtrAgt()
                            .getFinInstnId()
                            .getBICFI();
        }

        if (tx.getCdtrAgt() != null
                && tx.getCdtrAgt().getFinInstnId() != null) {

            receiverBic =
                    tx.getCdtrAgt()
                            .getFinInstnId()
                            .getBICFI();
        }

        if (senderBic != null
                && receiverBic != null
                && senderBic.equalsIgnoreCase(receiverBic)) {

            throw new IllegalArgumentException(
                    "Sender and Receiver BIC cannot be the same");
        }

        String senderAccount = null;
        String receiverAccount = null;

        if (tx.getDbtrAcct() != null
                && tx.getDbtrAcct().getId() != null
                && tx.getDbtrAcct().getId().getOthr() != null) {

            senderAccount =
                    tx.getDbtrAcct()
                            .getId()
                            .getOthr()
                            .getId();
        }

        if (tx.getCdtrAcct() != null
                && tx.getCdtrAcct().getId() != null
                && tx.getCdtrAcct().getId().getOthr() != null) {

            receiverAccount =
                    tx.getCdtrAcct()
                            .getId()
                            .getOthr()
                            .getId();
        }

        validateSenderAccount(senderAccount);
        validateReceiverAccount(receiverAccount);
    }

    private void validateSenderAccount(
            String accountNumber) {

        if (accountNumber == null
                || accountNumber.isBlank()) {

            throw new IllegalArgumentException(
                    "Sender account is missing");
        }

        if (!accountNumber.matches("^FI\\d{3}\\d+$")) {

            throw new IllegalArgumentException(
                    "Sender account must follow format FI + 3 digit transit + account number");
        }
    }

    private void validateReceiverAccount(
            String accountNumber) {

        if (accountNumber == null
                || accountNumber.isBlank()) {

            throw new IllegalArgumentException(
                    "Receiver account is missing");
        }

        if (!accountNumber.matches("^RI\\d{3}\\d+$")) {

            throw new IllegalArgumentException(
                    "Receiver account must follow format RI + 3 digit transit + account number");
        }
    }
}