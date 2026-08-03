package com.rbc.iso20022.parser;

import java.io.StringReader;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

public class Pacs008Parser {

    private Pacs008Parser() {
    }

    public static <T> T parse(String xml,
                              Class<T> clazz) {

        try {

            JAXBContext context =
                    JAXBContext.newInstance(clazz);

            Unmarshaller unmarshaller =
                    context.createUnmarshaller();

            return clazz.cast(
                    unmarshaller.unmarshal(
                            new StringReader(xml)));

        } catch (Exception e) {

            throw new RuntimeException(
                    "Invalid XML", e);
        }
    }
}