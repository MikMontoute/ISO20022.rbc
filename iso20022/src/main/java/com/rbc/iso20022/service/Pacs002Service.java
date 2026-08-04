package com.rbc.iso20022.service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class Pacs002Service {

    public String buildSuccessResponse(String originalMsgId) {

        String pacs002MsgId =
                "PACS002-" + UUID.randomUUID();

        String creationDateTime =
                OffsetDateTime.now()
                        .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <Document xmlns="urn:iso:std:iso:20022:tech:xsd:pacs.002.001.14">
                    <FIToFIPmtStsRpt>

                        <GrpHdr>
                            <MsgId>%s</MsgId>
                            <CreDtTm>%s</CreDtTm>
                        </GrpHdr>

                        <OrgnlGrpInfAndSts>
                            <OrgnlMsgId>%s</OrgnlMsgId>
                            <OrgnlMsgNmId>pacs.008.001.12</OrgnlMsgNmId>
                            <GrpSts>ACTC</GrpSts>
                        </OrgnlGrpInfAndSts>

                    </FIToFIPmtStsRpt>
                </Document>
                """
                .formatted(
                        pacs002MsgId,
                        creationDateTime,
                        originalMsgId);
    }

    public String buildRejectedResponse(
            String originalMsgId,
            String reasonCode,
            String reasonText) {

        String pacs002MsgId =
                "PACS002-" + UUID.randomUUID();

        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <Document xmlns="urn:iso:std:iso:20022:tech:xsd:pacs.002.001.14">
                    <FIToFIPmtStsRpt>

                        <GrpHdr>
                            <MsgId>%s</MsgId>
                            <CreDtTm>%s</CreDtTm>
                        </GrpHdr>

                        <OrgnlGrpInfAndSts>

                            <OrgnlMsgId>%s</OrgnlMsgId>

                            <OrgnlMsgNmId>
                                pacs.008.001.12
                            </OrgnlMsgNmId>

                            <GrpSts>RJCT</GrpSts>

                            <StsRsnInf>

                                <Rsn>
                                    <Cd>%s</Cd>
                                </Rsn>

                                <AddtlInf>%s</AddtlInf>

                            </StsRsnInf>

                        </OrgnlGrpInfAndSts>

                    </FIToFIPmtStsRpt>
                </Document>
                """
                .formatted(
                        pacs002MsgId,
                        OffsetDateTime.now(),
                        originalMsgId,
                        reasonCode,
                        reasonText);
    }
}