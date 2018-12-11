package com.lm.demo.sax;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class SAXGeneratorXml {

    public static void generateXml(String outputPath) {
        Person[] arr  = new Person[]{new Person("Lily" , 21) ,
                                        new Person("Silly" , 22)};
        List<Person> list = Arrays.asList(arr);
        Document doc = generateXML(list);
        try {
            outputXml(doc , outputPath);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("出现异常");
        }
    }

    /**
     * 将XML文件输出到指定的路径
     * @param doc
     * @param fileName
     * @throws TransformerException
     * @throws FileNotFoundException
     */
    public static void outputXml (Document doc , String fileName) throws TransformerException, FileNotFoundException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource(doc);
        transformer.setOutputProperty(OutputKeys.ENCODING , "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT , "yes");// 设置文档的换行与缩进
        PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
        StreamResult result = new StreamResult(pw);
        transformer.transform(source , result);
        System.out.println("生成XML文件成功");
    }

    /**
     * 生成XML文件
     * @param list
     * @return
     */
    public static Document generateXML (List<Person> list) {
        Document doc = null;
        Element root = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.newDocument();
            root = doc.createElement("person");
            doc.appendChild(root);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Element element;
        for (int i = 0; i < list.size(); i++) {
            Person person = list.get(i);
            element = doc.createElement("person" + (i + 1));
            element.setAttribute("age" , "" + person.getAge());
            element.setAttribute("name" , "" + person.getName());
            root.appendChild(element);
        }
        return doc;
    }

    public static void main(String[] args) {
        String outputPath = "C:/person.xml";
        generateXml(outputPath);
    }
}
