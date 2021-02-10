package com.Consumer;


import com.AllConverters;
import com.Model.Person;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConsumerJson {

    public static List<Person> personList = new ArrayList<Person>();

    public ConsumerJson() throws NamingException, JMSException {
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
                        System.out.println("Received message TEST: " + AllConverters.toJavaObject(textMessage.getText()));
                        Person finalPerson = AllConverters.toJavaObject(textMessage.getText());
                        personList.add(finalPerson);
                        //System.out.println("проверка" + personList);
                        System.out.println("тест получения: " + ConsumerJson.personList);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        } finally {
            //connection.close();
        }
    }
}
