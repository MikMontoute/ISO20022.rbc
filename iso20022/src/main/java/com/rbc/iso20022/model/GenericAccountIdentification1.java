package com.rbc.iso20022.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class GenericAccountIdentification1 {

    @XmlElement(
            name = "Id",
            namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12")
    private String id;

    public String getId() {
        return id;
    }
}