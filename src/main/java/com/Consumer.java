package com;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.w3c.dom.*;

import javax.jms.*;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;

public class Consumer {

    public static List<Document> documentList = new ArrayList<Document>();

    public Consumer() throws NamingException, JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("failover://tcp://localhost:61616");
        Connection connection;
        connection = connectionFactory.createConnection();
        try {
            connection.start();
            final Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            final Destination destination = session.createQueue("prospring4");
            MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("Received message: " + textMessage.getText());
                        Document document = AllConverters.stringToDocument(textMessage.getText());
                        AllConverters.addElementValue(document);
                        AllConverters.updateElementValueDispatched(document, 1);
                        documentList.add(document);
                        AllConverters.writeDocument(document);
                        //System.out.println(documentList.size());
                        System.out.println("Received message: " + AllConverters.documentToString(document));
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        } finally {
            //  connection.close();
        }
        /*ДЛЯ ПОЛУЧЕНИЯ ОБЪЕКТОВ В JSON
                    try {
                        try {
                            System.out.println("Received message TEST: " + Converter.toJavaObject(textMessage.getText()));
                            finalPerson = AllConverters.toJavaObject(textMessage.getText());
                            System.out.println("проверка" + personList);
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                     */
    }
}
