package com.lm.demo.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DomTest {

    /* build a DocumentBuilderFactory */
    private DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

    public static void main(String[] args) {
        DomTest parser = new DomTest();
        Document document = parser.parse("E:\\ideaProjects\\Java-Operation-XML\\Java-Operation-XML\\src\\main\\resources\\static\\books.xml");
        /* get root element */
        Element rootElement = document.getDocumentElement();
        /* get all the nodes whose name is book */
        NodeList nodeList = rootElement.getElementsByTagName("book");
        if (nodeList != null) {
            for (int i = 0; i < nodeList.getLength() ; i++) {
                /* get every node */
                Node node = nodeList.item(i);
                /* get the next lever's ChildNodes */
                NodeList nodeList1 = node.getChildNodes();
                for (int j = 0; j < nodeList1.getLength() ; j++) {
                    Node node1 = nodeList1.item(j);
                    if (node1.hasChildNodes()) {
                        System.out.println(node1.getNodeName() + " : "
                            + node1.getFirstChild().getNodeValue());
                    }
                }
            }
        }
    }

    /* Load and parse XML file into DOM */
    public Document parse (String filePath) {
        Document document = null;
        try {
            /* DOM parser instance */
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            /* parse an XML file into a DOM tree */
            document = builder.parse(filePath);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

}
