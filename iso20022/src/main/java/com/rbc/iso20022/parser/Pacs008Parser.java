package com.rbc.iso20022.parser;

import java.io.StringReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.rbc.iso20022.model.Document;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

public class Pacs008Parser {

    private Pacs008Parser() {
    }

    public static Document parse(String xml) {

        try {

            // Defense-in-depth: reject any DOCTYPE declaration
            if (xml.contains("<!DOCTYPE")) {

                throw new IllegalArgumentException(
                        "DOCTYPE declarations are not allowed");
            }

            SAXParserFactory spf =
                    SAXParserFactory.newInstance();

            spf.setNamespaceAware(true);

            spf.setFeature(
                    XMLConstants.FEATURE_SECURE_PROCESSING,
                    true);

            spf.setFeature(
                    "http://apache.org/xml/features/disallow-doctype-decl",
                    true);

            spf.setFeature(
                    "http://xml.org/sax/features/external-general-entities",
                    false);

            spf.setFeature(
                    "http://xml.org/sax/features/external-parameter-entities",
                    false);

            spf.setFeature(
                    "http://apache.org/xml/features/nonvalidating/load-external-dtd",
                    false);

            XMLReader xmlReader =
                    spf.newSAXParser().getXMLReader();

            SAXSource source =
                    new SAXSource(
                            xmlReader,
                            new InputSource(
                                    new StringReader(xml)));

            JAXBContext context =
                    JAXBContext.newInstance(Document.class);

            Unmarshaller unmarshaller =
                    context.createUnmarshaller();

            return (Document) unmarshaller.unmarshal(source);

        } catch (Exception ex) {

            throw new RuntimeException(
                    "Your PACS.008 message is not in the correct XML format",
                    ex);
        }
    }
}