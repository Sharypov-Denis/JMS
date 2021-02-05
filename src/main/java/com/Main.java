package com;

import com.Model.Person;

import javax.jms.JMSException;
import javax.naming.NamingException;


public class Main {

    public static void main(String[] args) throws JMSException {

        //Producer
        try {
            new Producer();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        //Consumer
        try {
            new Consumer();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        Person person = new Person(1, "nnn", 1);
        AllConverters.convertObjectToXml(person, "person.xml");
    }
}
