package com.jamindy.IOC.parser;

import org.dom4j.Document;
import org.dom4j.Element;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 15-4-28.
 */
public class ElementLoaderImpl implements ElementLoader {

    Map<String,Element> beanElements = new HashMap<String, Element>();

    @Override
    public void addBeanElements(Document doc) {
        List<Element> elementList = doc.getRootElement().elements();

        for(Element e : elementList){
            String id = e.attributeValue("id");
            beanElements.put(id,e);
        }

    }

    @Override
    public Element getBeanElement(String id) {
        return beanElements.get(id);
    }

    @Override
    public Collection<Element> getBeanElements() {
        return beanElements.values();
    }
}
