package com.rbc.iso20022.service;

import java.io.StringReader;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

@Service
public class XsdValidationService {

    private static final String XSD_PATH =
            "xsd/pacs.008.001.12.xsd";

    public void validate(String xml) {

        try {

            SchemaFactory factory =
                    SchemaFactory.newInstance(
                            XMLConstants.W3C_XML_SCHEMA_NS_URI);

            Schema schema =
                    factory.newSchema(
                            new ClassPathResource(
                                    XSD_PATH)
                                    .getFile());

            Validator validator =
                    schema.newValidator();

            validator.validate(
                    new StreamSource(
                            new StringReader(xml)));

        } catch (SAXException ex) {

            throw new IllegalArgumentException(
                    "PACS.008 failed XSD validation: "
                            + ex.getMessage());

        } catch (Exception ex) {

            throw new RuntimeException(
                    "Unable to validate PACS.008 XSD",
                    ex);
        }
    }
}