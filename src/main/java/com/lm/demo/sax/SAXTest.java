package com.lm.demo.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SAXTest {

    class BookHandler extends DefaultHandler{
        private List<String> nameList;
        private boolean title = false;

        public List<String> getNameList () {
            return nameList;
        }

        @Override
        public void startDocument() throws SAXException {
            System.out.println("start parsing document...");
            nameList = new ArrayList<>();
        }

        @Override
        public void endDocument() throws SAXException {
            System.out.println("end");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("title")) {
                title = true;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (title) {
                title = false;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (title) {
                String bookTitle = new String(ch , start , length);
                System.out.println("book title: " + bookTitle);
                nameList.add(bookTitle);
            }
        }
    }

    public static void main(String[] args) throws SAXException, IOException {
        XMLReader parser = XMLReaderFactory.createXMLReader();
        BookHandler bookHandler = (new SAXTest()).new BookHandler();
        parser.setContentHandler(bookHandler);
        parser.parse("E:\\ideaProjects\\Java-Operation-XML\\Java-Operation-XML\\src\\main\\resources\\static\\books.xml");
        System.out.println(bookHandler.getNameList());
    }
}
