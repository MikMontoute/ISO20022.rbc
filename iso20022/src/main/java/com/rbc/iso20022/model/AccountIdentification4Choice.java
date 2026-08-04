package com.rbc.iso20022.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class AccountIdentification4Choice {

    @XmlElement(
            name = "Othr",
            namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private GenericAccountIdentification1 othr;

    public GenericAccountIdentification1 getOthr() {
        return othr;
    }
}