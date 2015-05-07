package com.jamindy.IOC.context;

/**
 * Created by admin on 15-4-28.
 */
public interface ApplicationContext {

    Object getBean(String id);

    boolean isExist(String id);

    boolean isSingleton(String id);

    Object getBeanWithoutCreate(String id);


}
