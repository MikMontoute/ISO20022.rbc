package com.rbc.iso20022.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentIdentification {

    @XmlElement(name = "InstrId",
    		namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private String instrId;

    @XmlElement(name = "EndToEndId",
    		namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private String endToEndId;

    @XmlElement(name = "TxId",
    		namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private String txId;

	public Object getTxId() {
		// TODO Auto-generated method stub
		return txId;
	}

	public String getEndToEndId() {
		// TODO Auto-generated method stub
		return endToEndId;
	}
}