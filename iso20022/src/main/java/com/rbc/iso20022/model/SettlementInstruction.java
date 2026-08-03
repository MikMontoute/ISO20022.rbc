package com.rbc.iso20022.model;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class SettlementInstruction {

    @XmlElement(name = "SttlmMtd")
    private String sttlmMtd;
}
