package com.jamindy.IOC.bean;

import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by admin on 15-4-28.
 */
public interface PropertyHandler {

    Object setProperties(Object obj, Map<String,Object> propertyMap);

    Map<String,Method> getSetterMethodsMap(Object obj);

    /**
     * inject property by the setter method
     * @param obj
     * @param beanProperty
     * @param method
     */
    void executeMethod(Object obj, Object beanProperty, Method method);
}
