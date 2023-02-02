package com.kuark.tool.base.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rock
 * @detail
 * @date 2019/11/20 10:54
 */
public class ParseSOAPXml {
    public Map<String, Object> map = new HashMap<String, Object>();

    /**
     * 转换soap XML信息，获取子节点（参数）信息
     * @param soap
     * @return
     * @throws DocumentException
     */
    public Map parseSoap(String soap) throws DocumentException {
        Document doc = DocumentHelper.parseText(soap);//报文转成doc对象
        Element root = doc.getRootElement();//获取根元素，准备递归解析这个XML树
        getCode(root);
        return map;
    }

    public void getCode(Element root) {
        if (root.elements() != null) {
            List<Element> list = root.elements();//如果当前跟节点有子节点，找到子节点
            for (Element e : list) {//遍历每个节点
                if (e.elements().size() > 0) {
                    getCode(e);//当前节点不为空的话，递归遍历子节点；
                }
                if (e.elements().size() == 0) {
                    map.put(e.getName(), e.getTextTrim());
                }//如果为叶子节点，那么直接把名字和值放入map
            }
        }
    }
}
