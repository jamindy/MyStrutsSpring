package com.jamindy.IOC.bean;

import com.jamindy.IOC.exception.BeanCreateException;
import com.jamindy.IOC.util.IocUtil;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 15-4-28.
 */
public class BeanCreatorImpl implements BeanCreator {

    @Override
    public Object createBeanUseDefaultConstruct(String className) {
        try {
            Class<?> clazz = Class.forName(className);

            return clazz.newInstance();
        } catch (ClassNotFoundException e) {

            throw new BeanCreateException("没有找到" + className + "类", e);
        } catch (Exception e) {
            throw new BeanCreateException(e.getMessage());
        }
    }

    @Override
    public Object createBeanUseDefineConstruct(String className, List<Object> args) {
        Class<?>[] argsClasses = getArgsClass(args);

        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = getConstructor(clazz,argsClasses);

            return constructor.newInstance(args.toArray());
        } catch (ClassNotFoundException e) {
            throw new BeanCreateException("没有找到" + className + "类", e);
        } catch (NoSuchMethodException e){
            throw new BeanCreateException("没有找到" + className + "类中对应的构造方法", e);
        } catch (Exception e){
            throw new BeanCreateException(e.getMessage());
        }
    }

    public Class<?>[] getArgsClass(List<Object> args){
        List<Class<?>> result = new ArrayList<Class<?>>();
        for(Object obj : args){
            result.add(IocUtil.getClass(obj));
        }

        Class<?>[] a = new Class[result.size()];
        return result.toArray(a);
    }

    public Constructor<?> getConstructor(Class clazz,Class<?>[] argsClass) throws NoSuchMethodException{
        Constructor<?> constructor = clazz.getConstructor(argsClass);

        if(constructor == null){
            Constructor<?>[] constructors = clazz.getConstructors();

            for(Constructor<?> c : constructors){
                Class<?>[] tmpClass = c.getParameterTypes();

                if(argsClass.length == tmpClass.length){
                    if(isSameArgs(argsClass,tmpClass)){
                        return c;
                    }
                }
            }
        }else {
            return constructor;
        }

        throw new NoSuchMethodException("找不到指定的构造函数");
    }

    public boolean isSameArgs(Class<?>[] argsClass, Class<?>[] tmpClass){
        try {
            for(int i=0;i<argsClass.length;i++){
                argsClass[i].asSubclass(tmpClass[i]);

                if(i==argsClass.length-1){
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

}
