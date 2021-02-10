package com.Producer;

import com.AllConverters;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.w3c.dom.Document;

import javax.jms.*;
import javax.naming.NamingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ProducerXML {

    public ProducerXML() throws JMSException, NamingException {
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

            DocumentBuilder documentBuilder;
            Document document;
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = (Document) documentBuilder.parse("order.xml");
            AllConverters.updateElementValueTarget(document, 1);
            TextMessage message = session.createTextMessage(AllConverters.documentToString(document));
            producer.send(message);
            System.out.println("Sent message: " + message.getText());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //connection.close();
        }
    }
}