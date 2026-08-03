package com.rbc.iso20022.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbc.iso20022.model.Pacs008;
import com.rbc.iso20022.parser.Pacs008Parser;
import com.rbc.iso20022.service.AuditService;
import com.rbc.iso20022.service.PaymentService;



@RestController

@RequestMapping("/payments")

public class PaymentController {



private final Pacs008Parser parser;

private final PaymentService service;

private final AuditService audit;
public boolean stat;


public PaymentController(
Pacs008Parser parser,
PaymentService service,
AuditService audit
){

this.parser=parser;
this.service=service;
this.audit=audit;

}



@PostMapping(
consumes="application/xml"
)

public ResponseEntity<?> receive(

@RequestHeader("X-Message-Id")
String headerId,


@RequestHeader("X-Origin-Bank")
String origin,


@RequestBody String xml

)
throws Exception {



audit.record(
"PAYMENT_RECEIVED",
headerId,
xml
);

Pacs008 pacs = parser.parse(xml);

service.process(pacs);

if (service.stat) {
	return ResponseEntity.ok().body("Payment accepted");}else {
	return ResponseEntity.badRequest().body("Your request did not pass validations");	
}



}



}