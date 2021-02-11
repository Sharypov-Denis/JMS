package com.XSLTConvert;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class XSLTConvertAll {

    public static void XSLTConvert(File inputNameXml) {
        String nameNewXml = LocalDateTime.now().format(DateTimeFormatter.ofPattern("d-M-uuuu-HH-mm-ss"));
        File newFile = new File("D:\\ProjectJava\\TaxiStation\\orderXSLT-" + nameNewXml + ".xml");
        //Вариант №1
        /*
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Source xslDoc = new StreamSource("form.xsl");
            Source xmlDoc = new StreamSource("report.xml");
            String outputFileName = "report.html";
            OutputStream htmlFile = new FileOutputStream(outputFileName);
            Transformer trasformer = tFactory.newTransformer(xslDoc);
            trasformer.transform(xmlDoc, new StreamResult(htmlFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        //Вариант №2
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer1 = tf.newTransformer(new StreamSource("form.xsl")); //установка используемого XSL-преобразования
            transformer1.transform(//установка исходного XML-документа и конечного XML-файла
                    new StreamSource(inputNameXml),//исходный
                    new StreamResult(newFile));//конечный
            System.out.print("complete");
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        /*
        //Вариант №3
        // Source xslt = new StreamSource(new File(args[0]));
        Source xslt = new StreamSource("form1.xsl");
        Source xml  = new StreamSource("input.xml");
        Result out  = new StreamResult("out.xml");

        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");

        try {
            Transformer transformer = factory.newTransformer(xslt);
            transformer.transform(xml, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
         */
    }
}
