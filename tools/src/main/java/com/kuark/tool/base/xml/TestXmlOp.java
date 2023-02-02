package com.kuark.tool.base.xml;

import org.dom4j.Document;
import org.dom4j.Element;

/**
 * Created by caoqingyuan on 2016/7/26.
 */
public class TestXmlOp {
    public static void main(String[] args) throws IllegalAccessException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><id>11</id><name>测试</name><age>18</age><testVo>com.kuark.tool.base.vo.TestVo@648f53c8</testVo></root>";
        Document doc = XmlUtil.strToDom4jDoc(xml);
        Element rootElement = doc.getRootElement();
        System.out.println("rootName=" + rootElement.getName());
    }
}
