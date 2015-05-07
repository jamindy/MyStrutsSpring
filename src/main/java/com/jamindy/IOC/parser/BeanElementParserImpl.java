package com.jamindy.IOC.parser;

import com.jamindy.IOC.autowire.AutoWire;
import com.jamindy.IOC.autowire.ByNameAutowire;
import com.jamindy.IOC.autowire.NoAutoWire;
import com.jamindy.IOC.element.*;
import com.jamindy.IOC.util.IocUtil;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 15-4-28.
 */
public class BeanElementParserImpl implements BeanElementParser {

    /**
     * 自动装配
     * @param element
     * @return
     */
    @Override
    public AutoWire getAutowire(Element element) {

        String type = this.getAttribute(element, "autowire");

        String parentType = this.getAttribute(element.getParent(), "default-autowire");
        if ("no".equals(parentType)) {
            if ("byName".equals(type)) {
                return new ByNameAutowire(type);
            }
            return new NoAutoWire(type);
        } else if ("byName".equals(parentType)) {
            if ("no".equals(type)) {
                return new NoAutoWire(type);
            }

            return new ByNameAutowire(type);
        }

        return new NoAutoWire(type);
    }

    @Override
    public String getAttribute(Element e, String filedName) {
        return e.attributeValue(filedName);
    }

    @Override
    public List<Element> getConstructorArgsElements(Element e) {
        List<Element> list = e.elements();
        List<Element> constructorElements = new ArrayList<Element>();

        for(Element element : list){
            if("constructor-arg".equals(element.getName())){
                constructorElements.add(element);
            }
        }

        return constructorElements;
    }

    @Override
    public List<PropertyElement> getPropertyValue(Element element) {

        List<Element> properties = this.getPropertyElements(element);

        List<PropertyElement> result = new ArrayList<PropertyElement>();

        for (Element e : properties) {

            List<Element> eles = e.elements();

            LeafElement leafElement = getLeafElement(eles.get(0));

            String propertyNameAtt = this.getAttribute(e, "name");

            PropertyElement pe = new PropertyElement(propertyNameAtt, leafElement);

            result.add(pe);
        }
        /**
         * 返回PropertyElement元素的集合
         */
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Element> getPropertyElements(Element element) {

        List<Element> children = element.elements();

        List<Element> result = new ArrayList<Element>();

        for (Element e : children) {
            if("property".equals(e.getName())) {
                result.add(e);
            }
        }

        return result;
    }

    @Override
    public boolean isLazy(Element e) {
        String elementLazy = getAttribute(e,"lazy-init");
        Element parentElement = e.getParent();
        Boolean parentLazy = new Boolean(getAttribute(parentElement,"default-lazy-init"));

        if(parentLazy){
            if("false".equals(elementLazy)){
                return false;
            }
            return true;
        }else{
            if("true".equals(elementLazy)){
                return true;
            }
            return false;
        }
    }

    @Override
    public boolean isSingleton(Element e) {
        Boolean singleton = new Boolean(getAttribute(e,"singleton"));
        return singleton;
    }

    @Override
    public List<LeafElement> getConstructorValue(Element e) {
        List<Element> list = getConstructorArgsElements(e);
        List<LeafElement> leafElementList = new ArrayList<LeafElement>();

        for(Element ele : list){
            List<Element> elements = ele.elements();

            LeafElement leafElement = getLeafElement(elements.get(0));
            leafElementList.add(leafElement);
        }
        return leafElementList;
    }

    private LeafElement getLeafElement(Element e){
        String name = e.getName();
        String typeName = getAttribute(e,"type");
        String data = e.getText();
        if("value".equals(name)){
            return new ValueElement(IocUtil.getValue(typeName,data));
        }else if("ref".equals(name)){
            return new RefElement(getAttribute(e,"bean"));
        }else if("collection".equals(name)){
            return getCollectionElement(e);
        }

        return null;
    }

    private CollectionElement getCollectionElement(Element element){

        List<LeafElement> tmpList = new ArrayList<LeafElement>();

        String typeName = getAttribute(element,"type");

        CollectionElement collectionElement = new CollectionElement(typeName);

        List<Element> elementList = element.elements();
        for(Element e : elementList){
            String tmpName = e.getName();
            String leafTypeName = getAttribute(e,"type");
            String data = e.getText();
            if("value".equals(tmpName)){
                tmpList.add(new ValueElement(IocUtil.getValue(leafTypeName,data)));
            }else if("ref".equals(tmpName)){
                tmpList.add(new RefElement(getAttribute(e,"bean")));
            }
        }
        collectionElement.setList(tmpList);
        return collectionElement;
    }
}
