package com.rbc.iso20022.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(
        name = "Document",
        namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
public class Document {

    @XmlElement(name = "FIToFICstmrCdtTrf",
    			namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private FIToFICustomerCreditTransferV12 fiToFICstmrCdtTrf;

    public FIToFICustomerCreditTransferV12 getFIToFICstmrCdtTrf() {
        return fiToFICstmrCdtTrf;
    }
}
