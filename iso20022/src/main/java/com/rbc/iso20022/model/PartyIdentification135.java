package com.rbc.iso20022.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class PartyIdentification135 {

    @XmlElement(name = "Nm",
    		namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private String nm;

    @XmlElement(name = "PstlAdr",
    		namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private PostalAddress24 pstlAdr;
}