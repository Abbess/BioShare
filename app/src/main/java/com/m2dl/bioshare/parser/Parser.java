package com.m2dl.bioshare.parser;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Created by Abbes on 18/01/2015.
 */
public class Parser implements IParser {
    private DocumentBuilderFactory builderFactory = null;
    private DocumentBuilder builder = null;
    private Document xmlDocument;
    private XPath xPath;

    public Parser(InputStream inputStream) {
        builderFactory =
                DocumentBuilderFactory.newInstance();
        try {
            builder = builderFactory.newDocumentBuilder();
            xmlDocument = builder.parse(inputStream);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> getInitialElements() {
        ArrayList<String> elements = new ArrayList<>();

        xPath = XPathFactory.newInstance().newXPath();
        String expression = "//elements/element/name";

        try {
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            for(int i=0;i<nodeList.getLength();i++)
                elements.add(nodeList.item(i).getTextContent());


        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return elements;

    }

    @Override
    public ArrayList<String> getElementsByParentName(String parentName) {
        ArrayList<String> elements = new ArrayList<>();

        xPath = XPathFactory.newInstance().newXPath();
        String expression = "//element[name='"+parentName+"']/element/name";

        try {
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            for(int i=0;i<nodeList.getLength();i++)
                elements.add(nodeList.item(i).getTextContent());

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return elements;
    }
}
