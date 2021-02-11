package com.Producer;

import com.AllConverters;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.w3c.dom.Document;

import javax.jms.*;
import javax.naming.NamingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.concurrent.TimeUnit;

public class ProducerXMLTaxi {

    public ProducerXMLTaxi() throws JMSException, NamingException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("failover://tcp://localhost:61616");
        Connection connection;
        connection = connectionFactory.createConnection();
        try {
            connection.start();
            // для использования транзакций, установить для первого параметра значение true!!!
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("taxi");
            MessageProducer producer = session.createProducer(destination);

            DocumentBuilder documentBuilder;
            Document document;
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = (Document) documentBuilder.parse("order.xml");
           // for (int j = 0; j < 2; j++) {
                for (int i = 1; i < 2; i++) {
                    AllConverters.updateElementValueTarget(document, i);
                    TextMessage message = session.createTextMessage(AllConverters.documentToString(document));
                    producer.send(message);
                    System.out.println("Sent message: " + message.getText());
                    TimeUnit.SECONDS.sleep(1);
                }
            //}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //connection.close();
        }
    }
}