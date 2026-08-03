package com.rbc.iso20022.model;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class FIToFICustomerCreditTransferV12 {

    @XmlElement(name = "GrpHdr",
    		namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private GroupHeader113 grpHdr;

    @XmlElement(name = "CdtTrfTxInf",
    		namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private List<CreditTransferTransaction64> cdtTrfTxInf;

	public GroupHeader113 getGrpHdr() {
		// TODO Auto-generated method stub
		return grpHdr;
	}

	public List<CreditTransferTransaction64> getCdtTrfTxInf() {
		// TODO Auto-generated method stub
		return cdtTrfTxInf;
	}

}