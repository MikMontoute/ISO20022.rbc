package com.rbc.iso20022.parser;

import java.io.StringReader;

import com.rbc.iso20022.model.Document;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

public class Pacs008Parser {

    private Pacs008Parser() {
    }

    public static Document parse(String xml) {

        try {

            JAXBContext context =
                    JAXBContext.newInstance(Document.class);

            Unmarshaller unmarshaller =
                    context.createUnmarshaller();

            return (Document) unmarshaller.unmarshal(
                    new StringReader(xml));

        } catch (Exception ex) {

            throw new RuntimeException(
                    "Your PACS.008 message is not in the correct XML format",
                    ex);
        }
    }
}