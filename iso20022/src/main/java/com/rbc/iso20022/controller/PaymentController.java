package com.rbc.iso20022.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbc.iso20022.service.Pacs008Service;
import com.rbc.iso20022.validation.HeaderValidator;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

   private final Pacs008Service pacs008Service = new Pacs008Service();
    private final HeaderValidator headerValidator = new HeaderValidator();

    @PostMapping(
            value = "/pacs008",
            consumes = "application/xml",
            produces = "application/json")
    public ResponseEntity<?> receivePayment(
            @RequestHeader("X-Request-ID") String requestId,
            @RequestHeader("X-Correlation-ID") String correlationId,
            @RequestBody String xmlRequest) {

        headerValidator.validate(requestId, correlationId);

        return ResponseEntity.ok(
                pacs008Service.process(xmlRequest));
  
    }

}