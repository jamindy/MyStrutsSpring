package com.jamindy.IOC.parser;

import org.dom4j.Document;
import org.dom4j.Element;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ElementLoaderImplTest {
    XmlDocumentHolder xmlHolder;
    ElementLoader elementLoader;

    @Before
    public void setUp() throws Exception {
        xmlHolder = new XmlDocumentHolder();
        elementLoader = new ElementLoaderImpl();
    }

    @After
    public void tearDown() throws Exception {
        xmlHolder = null;
        elementLoader = null;
    }

    @Test
    public void testAddBeanElements() throws Exception {
        String filePath = ElementLoaderImplTest.class.getResource("/config.xml").getPath();
        Document document = xmlHolder.getDocument(filePath);
        assertNotNull(document);
        elementLoader.addBeanElements(document);
        Element e = elementLoader.getBeanElement("test1");
        assertNotNull(e);
        for(Iterator iter = elementLoader.getBeanElements().iterator();iter.hasNext();){
            System.out.println(iter.next());
        }
    }

    @Test
    public void testGetBeanElement() throws Exception {

    }

    @Test
    public void testGetBeanElements() throws Exception {

    }
}