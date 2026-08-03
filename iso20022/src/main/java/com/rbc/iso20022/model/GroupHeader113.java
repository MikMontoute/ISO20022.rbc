package com.rbc.iso20022.model;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class GroupHeader113 {

    @XmlElement(name="MsgId",
    		namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private String msgId;

    @XmlElement(name="CreDtTm",
    		namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private String creDtTm;

    @XmlElement(name="NbOfTxs",
    		namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private String nbOfTxs;

    @XmlElement(name="SttlmInf",
    		namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private SettlementInstruction sttlmInf;

	public String getMsgId() {
		// TODO Auto-generated method stub
		return msgId;
	}

	public String getNbOfTxs() {
		// TODO Auto-generated method stub
		return nbOfTxs;
	}
}
