package com.jamindy.IOC.context;

/**
 * Created by admin on 15-4-29.
 */
public class XmlApplicationContext extends AbstractApplicationContext {

    public XmlApplicationContext(String[] xmlPaths) {
        initElements(xmlPaths);

        createBeanInstances();
    }

}
