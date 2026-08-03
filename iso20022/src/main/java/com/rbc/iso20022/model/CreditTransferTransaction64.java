package com.rbc.iso20022.model;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class CreditTransferTransaction64 {

    @XmlElement(name = "PmtId",
    		namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private PaymentIdentification pmtId;

    @XmlElement(name = "IntrBkSttlmAmt",
    		namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private ActiveCurrencyAndAmount intrBkSttlmAmt;

    @XmlElement(name = "IntrBkSttlmDt",
    		namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private String intrBkSttlmDt;

    @XmlElement(name = "Dbtr",
    		namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private PartyIdentification135 dbtr;

    @XmlElement(name = "Cdtr",
    		namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private PartyIdentification135 cdtr;

	public ActiveCurrencyAndAmount getIntrBkSttlmAmt() {
		// TODO Auto-generated method stub
		return intrBkSttlmAmt;
	}
}
