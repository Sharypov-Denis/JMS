package com;

import com.Model.CityType;
import com.Model.Person;

import javax.jms.JMSException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;


public class Main {

    public static void main(String[] args) throws JMSException {
/*
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

 */

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
        Person person = new Person(1, "qwerty", 100);
        AllConverters.convertObjectToXml(person, "D:\\ProjectJava\\TaxiStation\\person.xml");

        CityType cityType = new CityType("qwerty", "11111");
        AllConverters.convertObjectToXml(cityType, "D:\\ProjectJava\\TaxiStation\\city.xml");

        ValidateXml("schema1.xsd","person.xml");
    }

    public static void ValidateXml(String fileSchema, String fileValidate) {
        //1. Поиск и создание экземпляра фабрики для языка XML Schema
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        // 2. Компиляция схемы
        // Схема загружается в объект типа java.io.File, но вы также можете использовать
        // классы java.net.URL и javax.xml.transform.Source
        File schemaLocation = new File(fileSchema);
        Schema schema = null;
        try {
            schema = factory.newSchema(schemaLocation);
        } catch (org.xml.sax.SAXException e) {
            e.printStackTrace();
        }
        // 3. Создание валидатора для схемы
        Validator validator = schema.newValidator();
        // 4. Разбор проверяемого документа
        Source source = new StreamSource(fileValidate);
        // 5. Валидация документа
        try {
            validator.validate(source);
            System.out.println(fileValidate + " is valid.");
        } catch (Exception ex) {
            System.out.println(fileValidate + " is not valid because ");
            System.out.println(ex.getMessage());
        }
    }
}

    /*Валидация схемы XML...
    //1. Поиск и создание экземпляра фабрики для языка XML Schema
    SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
    // 2. Компиляция схемы
    // Схема загружается в объект типа java.io.File, но вы также можете использовать
    // классы java.net.URL и javax.xml.transform.Source
    File schemaLocation = new File("schema1.xsd");
    Schema schema = null;
        try {
                schema = factory.newSchema(schemaLocation);
                } catch (org.xml.sax.SAXException e) {
                e.printStackTrace();
                }
                // 3. Создание валидатора для схемы
                Validator validator = schema.newValidator();
                // 4. Разбор проверяемого документа
                Source source = new StreamSource("person.xml");
                // 5. Валидация документа
                try {
                validator.validate(source);
                System.out.println("person.xml" + " is valid.");
                } catch (Exception ex) {
                System.out.println("person.xml" + " is not valid because ");
                System.out.println(ex.getMessage());
                }
*/