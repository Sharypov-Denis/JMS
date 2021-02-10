package com.Producer;

import com.AllConverters;
import com.Model.Person;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.NamingException;


public class ProducerJson {

    public ProducerJson() throws JMSException, NamingException {
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

            Person person = new Person(1, "qqqq", 1);
            String personToJson = AllConverters.toJSON(person);
            System.out.println(personToJson);
            TextMessage message = session.createTextMessage(personToJson);
            producer.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //connection.close();
        }

    }
}