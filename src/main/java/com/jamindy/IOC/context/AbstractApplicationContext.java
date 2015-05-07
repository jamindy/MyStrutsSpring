package com.jamindy.IOC.context;

import com.jamindy.IOC.Log;
import com.jamindy.IOC.autowire.AutoWire;
import com.jamindy.IOC.autowire.ByNameAutowire;
import com.jamindy.IOC.autowire.NoAutoWire;
import com.jamindy.IOC.bean.BeanCreator;
import com.jamindy.IOC.bean.BeanCreatorImpl;
import com.jamindy.IOC.bean.PropertyHandler;
import com.jamindy.IOC.bean.PropertyHandlerImpl;
import com.jamindy.IOC.element.*;
import com.jamindy.IOC.exception.BeanCreateException;
import com.jamindy.IOC.parser.*;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by admin on 15-4-28.
 */
public class AbstractApplicationContext implements ApplicationContext {

    protected DocumentHolder documentHolder = new XmlDocumentHolder();

    protected ElementLoader elementLoader = new ElementLoaderImpl();

    protected BeanElementParser beanElementParser = new BeanElementParserImpl();

    protected BeanCreator beanCreator = new BeanCreatorImpl();

    protected PropertyHandler propertyHandler = new PropertyHandlerImpl();

    protected Map<String, Object> beanInstanceMap = new HashMap<String, Object>();


    protected void initElements(String[] xmlPaths) {

        try {
            URL pathURL = AbstractApplicationContext.class.getClassLoader().getResource(".");
            String classPath = URLDecoder.decode(pathURL.getPath(), "utf-8");

            for (String xmlPath : xmlPaths) {
                Document doc = documentHolder.getDocument(classPath + xmlPath);
                elementLoader.addBeanElements(doc);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    protected Object createBeanInstance(String id) {
        Element e = elementLoader.getBeanElement(id);

        if (e == null) {
            throw new BeanCreateException("没有找到" + id + "对应的元素");
        }

        Object result = createBeanElement(e);
        Log.debug("创建bean:" + id + "对象是:" + result);

        //判断是否自动装配
        AutoWire autoWire = beanElementParser.getAutowire(e);
        if (autoWire instanceof ByNameAutowire) {
            autoWireByName(result);
        } else if (autoWire instanceof NoAutoWire) {
            setterInject(result, e);
        }

        return result;
    }

    protected Object createBeanElement(Element e) {

        String className = beanElementParser.getAttribute(e, "class");

        List<Element> constuctElements = beanElementParser.getConstructorArgsElements(e);

        if (constuctElements.size() == 0) {
            return beanCreator.createBeanUseDefaultConstruct(className);
        } else {
            List<Object> args = getConstructArgs(e);
            return beanCreator.createBeanUseDefineConstruct(className, args);
        }
    }

    protected void createBeanInstances() {
        Collection<Element> elements = elementLoader.getBeanElements();
        for (Element e : elements) {
            boolean lazy = beanElementParser.isLazy(e);
            if (!lazy) {
                String id = e.attributeValue("id");
                Object bean = getBean(id);
                if (bean == null) {
                    handleBean(id);
                }
            }
        }
    }

    protected Object handleBean(String id) {
        Object bean = createBeanInstance(id);

        if (isSingleton(id)) {
            this.beanInstanceMap.put(id, bean);
        }

        return bean;
    }

    protected void autoWireByName(Object obj) {
        Map<String,Method> methodMap = propertyHandler.getSetterMethodsMap(obj);

        for(String s : methodMap.keySet()){
            Element e = elementLoader.getBeanElement(s);

            if(e == null)
                continue;

            Object bean = getBean(s);

            Method method = methodMap.get(s);
            propertyHandler.executeMethod(obj,bean,method);
        }

    }

    //set obj properties
    protected void setterInject(Object obj, Element e) {
        List<PropertyElement> properties = beanElementParser.getPropertyValue(e);

        Map<String, Object> propertyMap = getPropertyArgs(properties);

        propertyHandler.setProperties(obj, propertyMap);

    }

    protected Map<String, Object> getPropertyArgs(List<PropertyElement> list) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        for (PropertyElement prop : list) {
            LeafElement leafElement = prop.getLeafElement();

            if (leafElement instanceof RefElement) {
                resultMap.put(prop.getName(), getBean((String) leafElement.getValue()));
            } else if (leafElement instanceof ValueElement) {
                resultMap.put(prop.getName(), leafElement.getValue());
            } else if (leafElement instanceof CollectionElement) {

                if (childIsValueElement((CollectionElement) leafElement)) {
                    if ("list".equals(leafElement.getType())) {
                        resultMap.put(prop.getName(), arrayToArrayList((Object[]) leafElement.getValue()));
                    } else {
                        resultMap.put(prop.getName(), arrayToHashSet((Object[]) leafElement.getValue()));
                    }
                } else {
                    if ("list".equals(leafElement.getType())) {
                        resultMap.put(prop.getName(), arrayToArrayList(getValuesIfChildIsRefElement(leafElement)));
                    } else {
                        resultMap.put(prop.getName(), arrayToHashSet(getValuesIfChildIsRefElement(leafElement)));
                    }
                }
            }
        }

        return resultMap;
    }

    protected List<Object> getConstructArgs(Element e) {
        List<LeafElement> datas  = beanElementParser.getConstructorValue(e);

        List<Object> args = new ArrayList<Object>();
        for(LeafElement element : datas){
            if(element instanceof ValueElement){
                args.add(element.getValue());
            }else if(element instanceof RefElement){
                args.add(getBean(element.getValue().toString()));
            }
        }

        return args;
    }

    protected boolean childIsValueElement(CollectionElement collectionElement) {

        if(collectionElement.getList().get(0) instanceof ValueElement){
            return true;
        }
        return false;
    }

    protected List<Object> arrayToArrayList(Object[] objects) {

        List<Object> list = new ArrayList<Object>();
        for(Object obj : objects){
            list.add(obj);
        }
        return list;
    }

    protected Set<Object> arrayToHashSet(Object[] objects) {
        Set<Object> sets = new HashSet<Object>();
        for(Object obj : objects){
            sets.add(obj);
        }
        return sets;
    }

    protected Object[] getValuesIfChildIsRefElement(LeafElement le) {

        List<Object> list = new ArrayList<Object>();
        for(Object obj : (Object[])le.getValue()){
            list.add(obj);
        }
        return list.toArray();
    }


    @Override
    public Object getBean(String id) {
        Object bean = beanInstanceMap.get(id);
        if(bean == null){
            bean = handleBean(id);
        }
        return bean;
    }


    @Override
    public boolean isExist(String id) {
        Element e = elementLoader.getBeanElement(id);

        return (e == null)? false:true;
    }

    @Override
    public boolean isSingleton(String id) {
        Element e = elementLoader.getBeanElement(id);

        return beanElementParser.isSingleton(e);
    }

    @Override
    public Object getBeanWithoutCreate(String id) {
        return this.beanInstanceMap.get(id);
    }
}
