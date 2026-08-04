package com.rbc.iso20022.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.rbc.iso20022.entity.PacsMessageEntity;
import com.rbc.iso20022.exception.DuplicateMessageException;
import com.rbc.iso20022.model.Document;
import com.rbc.iso20022.parser.Pacs008Parser;
import com.rbc.iso20022.repository.PacsMessageRepository;

@Service
public class Pacs008Service {
	


    private final PacsMessageRepository repository;

    public Pacs008Service(
            PacsMessageRepository repository) {

        this.repository = repository;
    }

    public String process(
            String xml,
            String correlationId) {

        Document document =
                Pacs008Parser.parse(xml);
        
  //  	TestLombok test = new TestLombok();
    //	test.getName();

        String messageId =
                extractMessageId(document);

        if (repository.existsByMessageId(messageId)) {

            throw new DuplicateMessageException(
                    "Duplicate PACS.008 MessageId: "
                            + messageId);
        }
        var payment = document.getFIToFICstmrCdtTrf();

        var tx = payment.getCdtTrfTxInf().get(0);
        
        
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

        entity.setMessageId(
                messageId);

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

        entity.setStatus(
                "PROCESSED");

        entity.setReceivedTimestamp(
                LocalDateTime.now());

        entity.setOriginalXml(xml);
        
        repository.save(entity);

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

        if (messageId == null ||
                messageId.isBlank()) {

            throw new IllegalArgumentException(
                    "MsgId missing");
        }

        return messageId;
    }
}