package com.jamindy.IOC.parser;

import com.jamindy.IOC.autowire.AutoWire;
import com.jamindy.IOC.element.LeafElement;
import com.jamindy.IOC.element.PropertyElement;
import org.dom4j.Element;

import java.util.List;

/**
 * Created by admin on 15-4-28.
 */
public interface BeanElementParser {

    AutoWire getAutowire(Element e);

    String getAttribute(Element e,String filedName);

    List<Element> getConstructorArgsElements(Element e);

    List<PropertyElement> getPropertyValue(Element e);

    public List<Element> getPropertyElements(Element element);

    boolean isLazy(Element e);

    boolean isSingleton(Element e);

    List<LeafElement> getConstructorValue(Element e);


}
