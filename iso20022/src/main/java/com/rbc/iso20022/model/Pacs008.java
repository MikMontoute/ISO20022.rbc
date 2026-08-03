package com.rbc.iso20022.model;


import jakarta.xml.bind.annotation.*;


@XmlRootElement(name="Document")
@XmlAccessorType(XmlAccessType.FIELD)

public class Pacs008 {


    @XmlElement(name="MsgId")
    private String messageId;


    @XmlElement(name="TxId")
    private String transactionId;



    @XmlElement(name="Dbtr")
    private String debtor;



    @XmlElement(name="Cdtr")
    private String creditor;



    @XmlElement(name="IntrBkSttlmAmt")
    private String amount;



    public String getMessageId(){
        return messageId;
    }


    public String getTransactionId(){
        return transactionId;
    }


    public String getDebtor(){
        return debtor;
    }


    public String getCreditor(){
        return creditor;
    }


    public String getAmount(){
        return amount;
    }

}