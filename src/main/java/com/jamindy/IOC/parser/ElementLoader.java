package com.jamindy.IOC.parser;

import org.dom4j.Document;
import org.dom4j.Element;

import java.util.Collection;

/**
 * Created by admin on 15-4-28.
 */
public interface ElementLoader {

    void addBeanElements(Document doc);

    Element getBeanElement(String id);

    Collection<Element> getBeanElements();
}
