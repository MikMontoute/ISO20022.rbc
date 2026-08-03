package com.rbc.iso20022.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "AUDIT_EVENT")
public class AuditEvent {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String eventType;


    private String messageId;


    @Column(length = 4000)
    private String payload;


    private LocalDateTime timestamp;



    // Required by JPA
    protected AuditEvent() {
    }



    public AuditEvent(
            String eventType,
            String messageId,
            String payload
    ) {

        this.eventType = eventType;
        this.messageId = messageId;
        this.payload = payload;
        this.timestamp = LocalDateTime.now();

    }



    public Long getId() {
        return id;
    }



    public String getEventType() {
        return eventType;
    }



    public String getMessageId() {
        return messageId;
    }



    public String getPayload() {
        return payload;
    }



    public LocalDateTime getTimestamp() {
        return timestamp;
    }


}