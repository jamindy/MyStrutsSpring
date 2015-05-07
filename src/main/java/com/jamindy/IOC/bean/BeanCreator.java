package com.jamindy.IOC.bean;

import java.util.List;

/**
 * Created by admin on 15-4-28.
 */
public interface BeanCreator {

    Object createBeanUseDefaultConstruct(String className);

    Object createBeanUseDefineConstruct(String className, List<Object> args);
}
