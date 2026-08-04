package com.rbc.iso20022.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CreditTransferTransaction64 {

    @XmlElement(
            name = "PmtId",
            namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private PaymentIdentification pmtId;

    @XmlElement(
            name = "IntrBkSttlmAmt",
            namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private ActiveCurrencyAndAmount intrBkSttlmAmt;

    @XmlElement(
            name = "IntrBkSttlmDt",
            namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private String intrBkSttlmDt;

    @XmlElement(
            name = "Dbtr",
            namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private PartyIdentification135 dbtr;

    @XmlElement(
            name = "Cdtr",
            namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private PartyIdentification135 cdtr;

    @XmlElement(
            name = "DbtrAcct",
            namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private CashAccount40 dbtrAcct;

    @XmlElement(
            name = "CdtrAcct",
            namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private CashAccount40 cdtrAcct;

    @XmlElement(
            name = "DbtrAgt",
            namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private BranchAndFinancialInstitutionIdentification8 dbtrAgt;

    @XmlElement(
            name = "CdtrAgt",
            namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private BranchAndFinancialInstitutionIdentification8 cdtrAgt;

    public PaymentIdentification getPmtId() {
        return pmtId;
    }

    public ActiveCurrencyAndAmount getIntrBkSttlmAmt() {
        return intrBkSttlmAmt;
    }

    public String getIntrBkSttlmDt() {
        return intrBkSttlmDt;
    }

    public PartyIdentification135 getDbtr() {
        return dbtr;
    }

    public PartyIdentification135 getCdtr() {
        return cdtr;
    }

    public CashAccount40 getDbtrAcct() {
        return dbtrAcct;
    }

    public CashAccount40 getCdtrAcct() {
        return cdtrAcct;
    }

    public BranchAndFinancialInstitutionIdentification8 getDbtrAgt() {
        return dbtrAgt;
    }

    public BranchAndFinancialInstitutionIdentification8 getCdtrAgt() {
        return cdtrAgt;
    }
}