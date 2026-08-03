package com.rbc.iso20022.service;


import org.springframework.stereotype.Service;

import com.rbc.iso20022.entity.AuditEvent;
import com.rbc.iso20022.repository.AuditRepository;



@Service

public class AuditService {


private final AuditRepository repo;


public AuditService(AuditRepository repo){

    this.repo=repo;

}



public void record(
        String type,
        String msgId,
        String payload
){

    repo.save(
        new AuditEvent(
            type,
            msgId,
            payload
        )
    );

}



}