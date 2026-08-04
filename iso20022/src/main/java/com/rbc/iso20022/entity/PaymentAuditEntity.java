package com.rbc.iso20022.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "PAYMENT_AUDIT")
public class PaymentAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String correlationId;

    private String requestId;

    private String messageId;

    private String eventType;

    private String eventDetail;

    private String status;

    private LocalDateTime eventTimestamp;

    @Lob
    private String payload;

    public Long getId() {
        return id;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventDetail() {
        return eventDetail;
    }

    public void setEventDetail(String eventDetail) {
        this.eventDetail = eventDetail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(
            LocalDateTime eventTimestamp) {

        this.eventTimestamp = eventTimestamp;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        
    }
}
