package com.jamindy.IOC.parser;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 15-4-28.
 */
public class XmlDocumentHolder implements DocumentHolder {

    private Map<String, Document> documents = new HashMap<String, Document>();

    /**
     * 根据xml文件的路径得到dom4j里面的Document对象
     * @param filePath
     * @return
     */
    @Override
    public Document getDocument(String filePath) {
        Document doc = this.documents.get(filePath);

        if (doc == null) {
            //使用SAXReader来读取xml文件
            this.documents.put(filePath, this.readDocument(filePath));
        }
        return this.documents.get(filePath);
    }
    /**
     * 根据文件的路径读取出Document对象，该方法是准备被下面的getDocument方法调用的
     * 所以定义成了private
     * @param filePath
     * @return
     */
    private Document readDocument(String filePath) {
        try {
            SAXReader reader = new SAXReader(true);
            reader.setEntityResolver(new XmlEntityResolver());

            File xmlFile = new File(filePath);
            Document document = reader.read(xmlFile);
            return document;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
