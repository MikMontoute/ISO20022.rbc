package com.rbc.iso20022.model;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class PostalAddress24 {

    @XmlElement(name = "StrtNm")
    private String streetName;

    @XmlElement(name = "BldgNb")
    private String buildingNumber;

    @XmlElement(name = "TwnNm")
    private String townName;

    @XmlElement(name = "Ctry")
    private String country;

    @XmlElement(name = "AdrLine")
    private List<String> addressLines;
}
