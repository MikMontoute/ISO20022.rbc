# ISO20022.rbc
BiMPay type challenge
===========================================================================
Thank you for the challenge, reminds me of UWI.
Dont be too disappointed, its been a while. (^_^)
I'll be back more prepared.
===========================================================================


SETUP Option 1

Clone Project: 
git clone https://github.com/MikMontoute/ISO20022.rbc.git
or
gh repo clone MikMontoute/ISO20022.rbc

open the project in an IDE like eclipse
right click on Iso20022Application
hover on Run As
select Java Application

Setup Option 2
Clone Project: 
git clone https://github.com/MikMontoute/ISO20022.rbc.git
or
gh repo clone MikMontoute/ISO20022.rbc

()you may have to unzip the target folder
in the target folder you will see the .jar file
cmd to the folder with the jar file
enter the command java -jar pacs008-service-1.0.0.jar

====================================================================================================================

Post Application Execution:
-----------------------------

Once the application is runnning on your localhost feel free to send curl messages to the below endpoint

sample curl:
curl.exe -X POST "http://localhost:8080/payments/pacs008" -H "Content-Type: application/xml" -H "Accept: application/xml" -H "X-Request-ID: REQ-20260803-0001" -H "X-Correlation-ID: CORR-20260803-0001" --data-binary "@pacs008.xml"

POST http://localhost:8080/payments/pacs008

Content-Type: application/xml
Accept: application/xml
X-Request-ID: REQ-NNN
X-Correlation-ID: CORR-NNN

SAMPLE XML:
<?xml version="1.0" encoding="UTF-8"?>
<Document xmlns="urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12">
    <FIToFICstmrCdtTrf>

        <GrpHdr>
            <MsgId>MSGID202608032120</MsgId>
            <CreDtTm>2026-08-03T10:15:30Z</CreDtTm>
            <NbOfTxs>1</NbOfTxs>
        </GrpHdr>

        <CdtTrfTxInf>

            <PmtId>
                <InstrId>INST000001</InstrId>
                <EndToEndId>E2E000001</EndToEndId>
                <TxId>TX000001</TxId>
            </PmtId>

            <IntrBkSttlmAmt Ccy="USD">
                150.00
            </IntrBkSttlmAmt>

            <IntrBkSttlmDt>
                2026-08-03
            </IntrBkSttlmDt>

            <Dbtr>
                <Nm>ABC Manufacturing Ltd</Nm>
            </Dbtr>

            <DbtrAcct>
                <Id>
                    <Othr>
                        <Id>FI123456789012</Id>
                    </Othr>
                </Id>
            </DbtrAcct>

            <DbtrAgt>
                <FinInstnId>
                    <BICFI>RBCCTTPSXXX</BICFI>
                </FinInstnId>
            </DbtrAgt>

            <Cdtr>
                <Nm>XYZ Trading Inc</Nm>
            </Cdtr>

            <CdtrAcct>
                <Id>
                    <Othr>
                        <Id>RI456987654321</Id>
                    </Othr>
                </Id>
            </CdtrAcct>

            <CdtrAgt>
                <FinInstnId>
                    <BICFI>REPUBBBBXXX</BICFI>
                </FinInstnId>
            </CdtrAgt>

        </CdtTrfTxInf>

    </FIToFICstmrCdtTrf>
</Document>