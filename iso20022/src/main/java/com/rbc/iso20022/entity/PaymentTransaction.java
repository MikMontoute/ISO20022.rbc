package com.rbc.iso20022.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="PAYMENT_TRANSACTION")

public class PaymentTransaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique=true)
    private String messageId;


    private String transactionId;


    private String debtor;


    private String creditor;


    private String amount;


    private LocalDateTime created;



    public Long getId() {
        return id;
    }


    public String getMessageId() {
        return messageId;
    }


    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }


    public String getTransactionId() {
        return transactionId;
    }


    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }


    public String getDebtor() {
        return debtor;
    }


    public void setDebtor(String debtor) {
        this.debtor = debtor;
    }


    public String getCreditor() {
        return creditor;
    }


    public void setCreditor(String creditor) {
        this.creditor = creditor;
    }


    public String getAmount() {
        return amount;
    }


    public void setAmount(String amount) {
        this.amount = amount;
    }


    public LocalDateTime getCreated() {
        return created;
    }


    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

}