package com.rbc.iso20022.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbc.iso20022.exception.DuplicateMessageException;
import com.rbc.iso20022.service.AuditService;
import com.rbc.iso20022.service.DigitalSignatureService;
import com.rbc.iso20022.service.Pacs002Service;
import com.rbc.iso20022.service.Pacs008Service;
import com.rbc.iso20022.validation.HeaderValidator;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final Pacs008Service pacs008Service;
    private final Pacs002Service pacs002Service;
    private final HeaderValidator headerValidator;
    private final AuditService auditService;
    private final DigitalSignatureService digitalSignatureService;
    
    public PaymentController(
            Pacs008Service pacs008Service,
            Pacs002Service pacs002Service,
            HeaderValidator headerValidator,
            AuditService auditService,
            DigitalSignatureService digitalSignatureService) {

        this.pacs008Service = pacs008Service;
        this.pacs002Service = pacs002Service;
        this.headerValidator = headerValidator;
        this.auditService = auditService;
        this.digitalSignatureService = digitalSignatureService;
    }

    
    
    @PostMapping(
    		
            value = "/pacs008",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    
    public ResponseEntity<String> receivePayment(

    		
            @RequestHeader("X-Request-ID")
            String requestId,

            @RequestHeader("X-Correlation-ID")
            String correlationId,

            @RequestBody String xmlRequest) {

        String originalMsgId = "UNKNOWN";

        try {
        	auditService.audit(
        	        correlationId,
        	        requestId,
        	        null,
        	        "PAYMENT_RECEIVED",
        	        "Inbound PACS.008 received",
        	        "RECEIVED",
        	        xmlRequest);
        	
            headerValidator.validate(
                    requestId,
                    correlationId);

            originalMsgId =
                    pacs008Service.getMessageId(
                            xmlRequest);

            pacs008Service.process(
                    xmlRequest,
                    correlationId);

            String pacs002 =
                    pacs002Service.buildSuccessResponse(
                            originalMsgId);
           
            pacs002 =  digitalSignatureService.signXml(pacs002);
            

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_XML)
                    .body(pacs002);
            

        } catch (DuplicateMessageException ex) {
        	
        	auditService.audit(
        	        correlationId,
        	        requestId,
        	        originalMsgId,
        	        "PAYMENT_REJECTED",
        	        ex.getMessage(),
        	        "FAILED",
        	        null);

            String pacs002 =
                    pacs002Service
                        .buildRejectedResponse(
                            originalMsgId,
                            "DUPL",
                            ex.getMessage());
            pacs002 =  digitalSignatureService.signXml(pacs002);

            return ResponseEntity
                    .status(409)
                    .contentType(MediaType.APPLICATION_XML)
                    .body(pacs002);
        } catch (IllegalArgumentException ex) {
        	
        	auditService.audit(
        	        correlationId,
        	        requestId,
        	        originalMsgId,
        	        "PAYMENT_REJECTED",
        	        ex.getMessage(),
        	        "FAILED",
        	        null);

            String pacs002 =
                    pacs002Service
                        .buildRejectedResponse(
                            originalMsgId,
                            "FF01",
                            ex.getMessage());
            pacs002 =  digitalSignatureService.signXml(pacs002);

            return ResponseEntity
                    .badRequest()
                    .contentType(MediaType.APPLICATION_XML)
                    .body(pacs002);
        } catch (Exception ex) {
        	
        	auditService.audit(
        	        correlationId,
        	        requestId,
        	        originalMsgId,
        	        "PAYMENT_REJECTED",
        	        ex.getMessage(),
        	        "FAILED",
        	        null);

            String pacs002 =
                    pacs002Service
                        .buildRejectedResponse(
                            originalMsgId,
                            "NARR",
                            ex.getMessage());
            pacs002 =  digitalSignatureService.signXml(pacs002);

            return ResponseEntity
                    .internalServerError()
                    .contentType(MediaType.APPLICATION_XML)
                    .body(pacs002);
        }
    }
}