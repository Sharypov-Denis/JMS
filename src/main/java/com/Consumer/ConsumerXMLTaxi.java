package com.Consumer;

import com.AllConverters;
import com.XSLTConvert.XSLTConvertAll;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.w3c.dom.Document;

import javax.jms.*;
import javax.naming.NamingException;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ConsumerXMLTaxi {
    public static List<Document> documentList = new ArrayList<Document>();

    public ConsumerXMLTaxi() throws NamingException, JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("failover://tcp://localhost:61616");
        Connection connection;
        connection = connectionFactory.createConnection();
        try {
            connection.start();
            final Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            final Destination destination = session.createQueue("taxi");
            MessageConsumer consumer = session.createConsumer(destination);

            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    TextMessage textMessage = (TextMessage) message;
                    String nameNewXml = LocalDateTime.now().format(DateTimeFormatter.ofPattern("d-M-uuuu-HH-mm-ss"));
                    String orderfinishXml= "order.xml";
                    File newFile = new File("D:\\ProjectJava\\TaxiStation\\order-" + nameNewXml + ".xml");
                    //System.out.println("test datd:" + finish);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        int a = 1; // Начальное значение диапазона - "от"
                        int b = 100; // Конечное значение диапазона - "до"
                        int random_number1 = a + (int) (Math.random() * b);
                        System.out.println("случайное число: "+ random_number1);
                       // System.out.println("Received message: " + textMessage.getText());
                        Document document = AllConverters.stringToDocument(textMessage.getText());
                        AllConverters.addElementValue(document);
                        AllConverters.updateElementValueDispatched(document,random_number1);
                        documentList.add(document);
                        AllConverters.writeDocument(document,newFile);
                        XSLTConvertAll.XSLTConvert(newFile);
                        //System.out.println(documentList.size());
                        System.out.println("Received message Final: " + AllConverters.documentToString(document));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } finally {
            //connection.close();
        }
    }
}