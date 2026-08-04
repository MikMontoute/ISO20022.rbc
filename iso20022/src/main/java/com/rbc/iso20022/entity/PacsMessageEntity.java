package com.rbc.iso20022.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(
    name = "PACS_MESSAGES",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "UK_MSG_ID",
            columnNames = "messageId"
        )
    }
)
public class PacsMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String messageId;

    private String txId;

    private String endToEndId;

    private String debtorName;

    private String creditorName;

    private String currency;

    private BigDecimal amount;

    private String correlationId;

    private String status;

    private LocalDateTime receivedTimestamp;
    private String debtorAccount;

    private String creditorAccount;

    private String debtorBic;

    private String creditorBic;

    @Lob
    private String originalXml;

    public void setMessageId(String messageId2) {
        this.messageId = messageId2;
    }

    public void setTxId(Object txId2) {
        this.txId = txId2 == null ? null : txId2.toString();
    }

    public void setEndToEndId(String endToEndId2) {
        this.endToEndId = endToEndId2;
    }

    public void setDebtorName(String nm) {
        this.debtorName = nm;
    }

    public void setCurrency(String currency2) {
        this.currency = currency2;
    }

    public void setCreditorName(String nm) {
        this.creditorName = nm;
    }

    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    public void setCorrelationId(String correlationId2) {
        this.correlationId = correlationId2;
    }

    public void setStatus(String string) {
        this.status = string;
    }

    public void setReceivedTimestamp(LocalDateTime now) {
        this.receivedTimestamp = now;
    }

    public void setOriginalXml(String xml) {
        
    }
    // Generate getters/setters

	public void setDebtorAccount(String id2) {
        this.debtorAccount = id2;
		
	}

	public void setCreditorAccount(String id2) {
		this.creditorAccount = id2; 
		
	}

	public void setDebtorBic(String bicfi) {
		this.debtorBic = bicfi;
		
	}

	public void setCreditorBic(String bicfi) {
		this.creditorBic = bicfi;
		
	}
	
	public String getDebtorBic() {
	    return debtorBic;
	}

	public String getCreditorBic() {
	    return creditorBic;
	}
}