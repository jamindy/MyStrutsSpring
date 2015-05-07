package com.jamindy.IOC.bean;

import com.jamindy.IOC.exception.BeanCreateException;
import com.jamindy.IOC.exception.PropertyException;
import com.jamindy.IOC.util.IocUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 15-4-28.
 */
public class PropertyHandlerImpl implements PropertyHandler {

    @Override
    public Object setProperties(Object obj, Map<String, Object> propertyMap) {
        Class<?> clazz = obj.getClass();

        try {
            for (String key : propertyMap.keySet()) {
                String setterName = getSetterMethodName(key);
                Class<?> argClass = IocUtil.getClass(propertyMap.get(key));
                Method setterMethod = getSetterMethod(clazz, setterName, argClass);

                setterMethod.invoke(obj, propertyMap.get(key));
            }

            return obj;
        } catch (NoSuchMethodException e) {
            throw new PropertyException("no setter method", e);
        } catch (IllegalArgumentException e) {
            throw new PropertyException("wrong argument", e);
        } catch (Exception e) {
            throw new PropertyException(e);
        }

    }

    private String getSetterMethodName(String propertyName) {
        return "set" + firstWordToUpperCase(propertyName);
    }

    private String firstWordToUpperCase(String s) {
        String firstStr = s.substring(0, 1);
        String firstUpperStr = firstStr.toUpperCase();
        return s.replaceFirst(firstStr, firstUpperStr);
    }

    private Method getSetterMethod(Class<?> clazz, String setterName, Class<?> argClass) throws NoSuchMethodException {
        Method argClassMethod = clazz.getMethod(setterName, argClass);

        if (argClassMethod == null) {
            List<Method> methods = getMethods(clazz, setterName);
            Method method = findMethod(argClass, methods);
            if (method == null) {
                throw new NoSuchMethodException(setterName);
            }

            return method;
        } else {
            return argClassMethod;
        }
    }

    private List<Method> getMethods(Class<?> clazz, String methodName) {
        List<Method> resultList = new ArrayList<Method>();

        for(Method method : clazz.getMethods()){
            if(method.getName().equals(methodName)){
                Class<?>[] arg = method.getParameterTypes();
                if(arg.length == 1){
                    resultList.add(method);
                }
            }
        }
        return resultList;
    }

    private Method findMethod(Class<?> argClass, List<Method> methods) {
        for (Method m : methods){
            if(isMethodArgs(m,argClass)){
                return m;
            }
        }
       return null;
    }

    private boolean isMethodArgs(Method method, Class<?> argClass){
        Class<?>[] argsClass = method.getParameterTypes();
        if(argsClass.length == 1){
            try {
                Class<?> tmpClass = argsClass[0];
                argClass.asSubclass(tmpClass);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public Map<String, Method> getSetterMethodsMap(Object obj) {
        List<Method> methods = getSetterMethodsList(obj);
        Map<String,Method> resutlMap = new HashMap<String, Method>();

        for(Method method : methods){
            String propName = getPropNameWithOutSet(method.getName());
            resutlMap.put(propName,method);
        }
        return resutlMap;
    }

    //get the set method list
    private List<Method> getSetterMethodsList(Object obj){
        Class clazz = obj.getClass();
        Method[] methods = clazz.getMethods();

        List<Method> resultList = new ArrayList<Method>();

        for(Method method : methods){
            if(method.getName().startsWith("set")){
                resultList.add(method);
            }
        }
        return resultList;
    }

    private String getPropNameWithOutSet(String setMethodName){
        String propName = setMethodName.substring(3);
        String firstWord = propName.substring(0,1);
        String lowerFirstWord = firstWord.toLowerCase();
        propName = propName.replaceFirst(firstWord,lowerFirstWord);
        return propName;
    }


    @Override
    public void executeMethod(Object obj, Object beanProperty, Method method) {
        try {
            Class<?>[] paramTypes = method.getParameterTypes();
            if(paramTypes.length == 1){
                method.invoke(obj,beanProperty);
            }
        } catch (Exception e) {
            throw new BeanCreateException("AutoWire property Exception",e);
        }
    }
}
