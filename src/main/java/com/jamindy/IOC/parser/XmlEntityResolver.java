package com.jamindy.IOC.parser;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by admin on 15-5-6.
 */
public class XmlEntityResolver implements EntityResolver {
    @Override
    public InputSource resolveEntity(String publicId, String systemId)
            throws SAXException, IOException {
        /**
         * 如果自己写的xml配置文件中引入dtd的时候publicId与"-//RONGDI//DTD BEAN//CN"相同
         * 并且systemId与"http://www.cnblogs.com/jamindy/beans.dtd"相同，就从本地的相对项目的路径
         * 寻找dtd,返回一个dtd的输入源，若果找不到该dtd就会尝试到对应的网址上寻找
         */
        if ("-//JAMINDY//DTD BEAN//CN".equals(publicId)&&"http://www.csdn.net/jamindy/beans.dtd".equals(systemId)) {
            InputStream stream = this.getClass().
                    getResourceAsStream("/com/jamindy/IOC/bean/dtd/beans.dtd");
            return new InputSource(stream);
        }
        return null;
    }
}
