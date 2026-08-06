package com.rbc.iso20022.service;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

@Service
public class DigitalSignatureService {

    @Value("${signature.keystore}")
    private String keystoreFile;

    @Value("${signature.alias}")
    private String alias;

    @Value("${signature.password}")
    private String password;

    public String signXml(String xml) {

        try {

            KeyStore keyStore =
                    KeyStore.getInstance("PKCS12");

            InputStream is =
                    new ClassPathResource(
                            keystoreFile)
                            .getInputStream();

            keyStore.load(
                    is,
                    password.toCharArray());

            PrivateKey privateKey =
                    (PrivateKey) keyStore.getKey(
                            alias,
                            password.toCharArray());

            X509Certificate certificate =
                    (X509Certificate)
                            keyStore.getCertificate(
                                    alias);

            DocumentBuilderFactory dbf =
                    DocumentBuilderFactory.newInstance();

            dbf.setNamespaceAware(true);

            Document document =
                    dbf.newDocumentBuilder()
                            .parse(
                                    new InputSource(
                                            new StringReader(xml)));

            XMLSignatureFactory factory =
                    XMLSignatureFactory.getInstance("DOM");

            Reference reference =
                    factory.newReference(
                            "",
                            factory.newDigestMethod(
                                    DigestMethod.SHA256,
                                    null),
                            java.util.List.of(
                                    factory.newTransform(
                                            Transform.ENVELOPED,
                                            (TransformParameterSpec) null)),
                            null,
                            null);

            SignedInfo signedInfo =
                    factory.newSignedInfo(
                            factory.newCanonicalizationMethod(
                                    CanonicalizationMethod.INCLUSIVE,
                                    (C14NMethodParameterSpec) null),
                            factory.newSignatureMethod(
                                    SignatureMethod.RSA_SHA256,
                                    null),
                            java.util.List.of(reference));

            KeyInfoFactory keyInfoFactory =
                    factory.getKeyInfoFactory();

            X509Data x509Data =
                    keyInfoFactory.newX509Data(
                            java.util.List.of(certificate));

            KeyInfo keyInfo =
                    keyInfoFactory.newKeyInfo(
                            java.util.List.of(x509Data));

            DOMSignContext signContext =
                    new DOMSignContext(
                            privateKey,
                            document.getDocumentElement());

            XMLSignature signature =
                    factory.newXMLSignature(
                            signedInfo,
                            keyInfo);

            signature.sign(signContext);

            TransformerFactory tf =
                    TransformerFactory.newInstance();

            Transformer transformer =
                    tf.newTransformer();

            StringWriter writer =
                    new StringWriter();

            transformer.transform(
                    new DOMSource(document),
                    new StreamResult(writer));

            return writer.toString();

        } catch (Exception ex) {

            throw new RuntimeException(
                    "Failed to sign PACS.002",
                    ex);
        }
    }
}