package com.rbc.iso20022.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class BranchAndFinancialInstitutionIdentification8 {

    @XmlElement(
            name = "FinInstnId",
            namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private FinancialInstitutionIdentification23 finInstnId;

    public FinancialInstitutionIdentification23 getFinInstnId() {
        return finInstnId;
    }
}