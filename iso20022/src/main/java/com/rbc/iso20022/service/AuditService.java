package com.rbc.iso20022.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.rbc.iso20022.entity.PaymentAuditEntity;
import com.rbc.iso20022.repository.PaymentAuditRepository;

@Service
public class AuditService {

    private final PaymentAuditRepository repository;

    public AuditService(
            PaymentAuditRepository repository) {

        this.repository = repository;
    }

    public void audit(
            String correlationId,
            String requestId,
            String messageId,
            String eventType,
            String eventDetail,
            String status,
            String payload) {

        PaymentAuditEntity audit =
                new PaymentAuditEntity();

        audit.setCorrelationId(correlationId);
        audit.setRequestId(requestId);
        audit.setMessageId(messageId);
        audit.setEventType(eventType);
        audit.setEventDetail(eventDetail);
        audit.setStatus(status);
        audit.setPayload(payload);
        audit.setEventTimestamp(LocalDateTime.now());

        repository.save(audit);
    }
}