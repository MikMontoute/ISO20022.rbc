package com.rbc.iso20022.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rbc.iso20022.model.Document;
import com.rbc.iso20022.parser.Pacs008Parser;

@Service
public class Pacs008Service {

    public Map<String, Object> process(String xml) {

        Document document = Pacs008Parser.parse(xml, Document.class);
        
        	System.out.println(document);
        	System.out.println("FIToFICstmrCdtTrf = " + document.getFIToFICstmrCdtTrf());

        Map<String, Object> response =
                new HashMap<>();

        response.put("messageId",
                document.getFIToFICstmrCdtTrf()
                        .getGrpHdr()
                        .getMsgId());

        response.put("transactionCount",
                document.getFIToFICstmrCdtTrf()
                        .getGrpHdr()
                        .getNbOfTxs());

        response.put("settlementAmount",
                document.getFIToFICstmrCdtTrf()
                        .getCdtTrfTxInf()
                        .get(0)
                        .getIntrBkSttlmAmt()
                        .getValue());

        return response;
    }
}
