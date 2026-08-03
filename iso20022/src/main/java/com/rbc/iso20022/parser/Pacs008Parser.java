package com.rbc.iso20022.parser;


import java.io.StringReader;

import org.springframework.stereotype.Component;

import com.rbc.iso20022.model.Pacs008;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;



@Component

public class Pacs008Parser {


public Pacs008 parse(String xml)
throws Exception{


    JAXBContext context =
        JAXBContext.newInstance(Pacs008.class);


    Unmarshaller unmarshaller =
        context.createUnmarshaller();


    return 
    (Pacs008)unmarshaller.unmarshal(
        new StringReader(xml)
    );

}


}