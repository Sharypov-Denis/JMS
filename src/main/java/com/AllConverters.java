package com;

import com.Model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class AllConverters {
    // конвертируем XML Document в строку
    public static String documentToString(Document doc) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource((Node) doc), new StreamResult(writer));
            return writer.getBuffer().toString();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }

    // конвертируем строку в XML Document
    public static Document stringToDocument(String xmlStr) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = builderFactory.newDocumentBuilder();
            return documentBuilder.parse(new InputSource(new StringReader(xmlStr)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // сохраняем DOM в xml файл
    public static void writeDocument(Document document, String nameFilexML) throws TransformerFactoryConfigurationError, IOException {
        File newFile = new File("D:\\ProjectJava\\TaxiStation\\order-" + nameFilexML + ".xml");
       // newFile.createNewFile();
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource domSource = new DOMSource(document);
            //FileOutputStream fileOutputStream = new FileOutputStream("orderfinish.xml");
            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
            StreamResult streamResult = new StreamResult(fileOutputStream);
            transformer.transform(domSource, streamResult);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    //вставка элемента
    public static void addElementValue(Document doc) {
        try {
            doc.normalize();
            Node target = doc.getElementsByTagName("target").item(0);
            NodeList languages = doc.getElementsByTagName("message");
            Element newElement = doc.createElement("dispatched");
            newElement.setAttribute("id", "0");
            target.getParentNode().insertBefore(newElement, target.getNextSibling());
        } catch (Exception e) {
            System.out.println("вставка не удаласть");
        }
    }

    //обновление элемента
    public static void updateElementValueTarget(Document doc, Integer targetId) {
        try {
            doc.normalize();
            Node target = doc.getElementsByTagName("target").item(0);
            NamedNodeMap attributes = target.getAttributes();
            Node id = attributes.getNamedItem("id");
            id.setTextContent(targetId.toString());
        } catch (Exception e) {
            System.out.println("обновление не удалось");
        }
    }

    //обновление элемента
    public static void updateElementValueDispatched(Document doc, Integer dispId) {
        try {
            doc.normalize();
            Node dispatched = doc.getElementsByTagName("dispatched").item(0);
            NamedNodeMap attributes1 = dispatched.getAttributes();
            Node id1 = attributes1.getNamedItem("id");
            id1.setTextContent(dispId.toString());
        } catch (Exception e) {
            System.out.println("обновление не удалось");
        }
    }

    //преобразование Объекта в JSON
    public static String toJSON(Person person) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(person);
        System.out.println("json created!");
        return jsonString;
    }

    //преобразование JSON в Объект
    public static Person toJavaObject(String string) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Person person = mapper.readValue(string, Person.class);
        System.out.println("json Uncreated!");
        return person;
    }

    // сохраняем объект в XML файл
    public static void convertObjectToXml(Person person, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Person.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(person, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    // восстанавливаем объект из XML файла
    public static Person fromXmlToObject(String filePath) {
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();
            return (Person) un.unmarshal(new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
