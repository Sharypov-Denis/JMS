package com;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.w3c.dom.*;

import javax.jms.*;
import javax.naming.NamingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class Producer {

    public Producer() throws JMSException, NamingException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("failover://tcp://localhost:61616");
        Connection connection;
        connection = connectionFactory.createConnection();
        try {
            connection.start();
            // для использования транзакций, установить для первого параметра значение true!!!
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("prospring4");
            MessageProducer producer = session.createProducer(destination);
            DocumentBuilder documentBuilder = null;
            Document document = null;
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = (Document) documentBuilder.parse("order.xml");

            //for (int i = 1; i < 12; i++) {
                AllConverters.updateElementValueTarget(document, 1);
                TextMessage message = session.createTextMessage(AllConverters.documentToString(document));
                producer.send(message);
                System.out.println("Sent message: " + message.getText());
           // }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //connection.close();
        }
        /* ДЛЯ ПЕРЕДАЧИ ОБЪЕКТОВ В JSON
            Person person = new Person("1","qqqq",1);
            String personToJson = AllConverters.toJSON(person);
            System.out.println(personToJson);
            TextMessage message = session.createTextMessage(personToJson);
         */
    }

}