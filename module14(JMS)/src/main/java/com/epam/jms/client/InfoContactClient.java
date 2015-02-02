package com.epam.jms.client;

import com.compumark.messaging.MessageType;
import com.compumark.messaging.MessagingException;
import com.compumark.messaging.MessagingMAO;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by Yahor_Karabitsyn on 2/2/2015.
 */
public class InfoContactClient {

    public void sendAndReadMessage(String userName) {
        try {
            MessagingMAO mao = MessagingMAO.getMAO("OPSiService");

            String request =
                    "<Request>"
                            + "<Option>GetSecLoginContact</Option>"
                            + "<SecLoginContactName>" + userName + "</SecLoginContactName>"
                            + "</Request>";

            String response = mao.sendAndRead(
                    request, MessageType.OPSI_INFOSERVER_MESSAGE, com.compumark.messaging.Message.PRIORITY_ONLINE, -1);

            parseContactInfo(response.getBytes("UTF-8"));
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private void parseContactInfo(byte[] bytes) {
        InputStream is = new ByteArrayInputStream(bytes);
        try {
            String userName = null;
            Long contactId = null;
            Long memberId = null;
            String role = null;

            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(is);
            while (reader.hasNext()) {
                int eventCode = reader.next();
                if (eventCode == XMLStreamConstants.START_ELEMENT) {
                    String localName = reader.getLocalName();

                    if (localName.equals("SecLoginContactName")) {
                        userName = reader.getElementText();
                    }

                    if (localName.equals("SecLoginContactId")) {
                        contactId = Long.valueOf(reader.getElementText());
                    }

                    if (localName.equals("MemberId")) {
                        memberId = Long.valueOf(reader.getElementText());
                    }

                    if (localName.equals("SecGroups")) {
                        role = getRole(reader);
                    }

                    if (localName.equals("Error")) {
                        System.out.println(reader.getElementText());
                    }
                }

                System.out.println(
                        "userName = " + userName
                                + ", contactId = " + contactId
                                + ", memberId = " + memberId
                                + ", role = " + role);
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }


    private String getRole(XMLStreamReader reader) throws XMLStreamException {
        String role = null;

        boolean finished = false;
        String localName = null;
        String productLine = null;
        while (!finished) {
            int eventCode = reader.next();
            switch (eventCode) {
                case XMLStreamConstants.START_ELEMENT:
                    localName = reader.getLocalName();

                    if (localName.equals("SecGroup")) {
                        role = null;
                        productLine = null;
                    }

                    if (localName.equals("ProductLineName")) {
                        productLine = reader.getElementText();
                    }

                    if (localName.equals("SecGroupName")) {
                        role = reader.getElementText();
                    }

                    break;
                case XMLStreamConstants.END_ELEMENT:
                    localName = reader.getLocalName();
                    if (localName.equals("SecGroup")) {
                        if (productLine != null &&
                                productLine.equalsIgnoreCase("ASSOCIATES")) {
                            finished = true;
                        }
                    }
                    if (localName.equals("SecGroups")) {
                        role = null;
                        finished = true;
                    }
                    break;
                default:
                    //do nothing
                    break;
            }
        }

        return role;
    }


}
