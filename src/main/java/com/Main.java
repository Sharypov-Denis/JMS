package com;

import com.Consumer.ConsumerXMLTaxi;
import com.Producer.ProducerXMLTaxi;

import javax.jms.JMSException;


public class Main {

    public static void main(String[] args) throws JMSException {

        Thread myThready = new Thread(new Runnable() {
            public void run() {
                try {
                    new ProducerXMLTaxi();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        myThready.start();

        Thread myThready2 = new Thread(new Runnable() {
            public void run() {
                try {
                    new ConsumerXMLTaxi();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        myThready2.start();
/*
        try {
            new ProducerJson();
            new ProducerXML();
            new ProducerXMLTaxi();
            new ConsumerXMLTaxi();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

 */
    }
}
