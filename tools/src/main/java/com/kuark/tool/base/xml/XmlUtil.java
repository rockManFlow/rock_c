package com.kuark.tool.base.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Iterator;

/**
 * Created by caoqingyuan on 2016/7/27.
 */
public class XmlUtil {
    /**
     * 只能转换实体中属性为基本数据库类型---到xml格式字符串
     * @param ob
     * @return
     * @throws IllegalAccessException
     */
    public static String objectToXml(Object ob) throws IllegalAccessException {
        //新创建一个document对象
        Document doc=DOM4JUtil.createDomFJ();
        doc.setXMLEncoding("UTF-8");//设置utf-8编码
        //创建根目录标签
        Element root=doc.addElement("root");
        Class c=ob.getClass();
        Field[] fields = c.getDeclaredFields();//获取属性名
        for(Field f:fields){
            f.setAccessible(true);//设置当前对象对私有属性的访问权限
            Element element=root.addElement(f.getName());
            element.setText(f.get(ob).toString());//因为该ob对象为父类对象，不能获取到子类对象的私有属性
        }
        String asxml=doc.asXML();//获取Dom对象的字符串形式
        String xml=asxml.replaceFirst("\\n","");
        return xml;
    }
    /**
     * 字符串转org.dom4j.Document.
     *
     * @param xml
     *            xml字符串
     * @return org.dom4j.Document文档对象
     * @author qingwu
     * @date 2013-6-26 上午10:00:00
     */
    public static org.dom4j.Document strToDom4jDoc(String xml) {
        try {
            return DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 文件转org.dom4j.Document.
     *
     * @param fileName
     *            以“/”开头，根据工程项目相对路径读取
     * @return org.dom4j.Document
     * @author qingwu
     * @date 2013-6-26 上午10:00:00
     */
    public static org.dom4j.Document fileToDocument(String fileName) throws FileNotFoundException {
        InputStream is = new FileInputStream(fileName);
        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            doc = reader.read(is);
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return doc;
    }

    /**
     * 输入流转org.dom4j.Document
     *
     * @param is
     *            输入流
     * @return org.dom4j.Document文档对象
     * @author qingwu
     * @date 2013-6-26 上午10:00:00
     */
    public static org.dom4j.Document inputStreamToDom4jDoc(InputStream is) {
        try {
            SAXReader reader = new SAXReader();
            return reader.read(is);
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 示例，解析xml字符串或文件
     * @param xml
     */
    public void readStringXml(String xml) {
        Document doc = null;
        try {
            // 读取并解析XML文档
            // SAXReader就是一个管道，用一个流的方式，把xml文件读出来
            // SAXReader reader = new SAXReader(); //User.hbm.xml表示你要解析的xml文档
            // Document document = reader.read(new File("User.hbm.xml"));
            // 下面的是通过解析xml字符串的

            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
            Iterator iter = rootElt.elementIterator("head"); // 获取根节点下的子节点head
            // 遍历head节点
            while (iter.hasNext()) {
                Element recordEle = (Element) iter.next();
                String title = recordEle.elementTextTrim("title"); // 拿到head节点下的子节点title值
                System.out.println("title:" + title);
                Iterator iters = recordEle.elementIterator("script"); // 获取子节点head下的子节点script
                // 遍历Header节点下的Response节点
                while (iters.hasNext()) {
                    Element itemEle = (Element) iters.next();
                    String username = itemEle.elementTextTrim("username"); // 拿到head下的子节点script下的字节点username的值
                    String password = itemEle.elementTextTrim("password");
                    System.out.println("username:" + username);
                    System.out.println("password:" + password);
                }
            }
            Iterator iterss = rootElt.elementIterator("body"); ///获取根节点下的子节点body
            // 遍历body节点
            while (iterss.hasNext()) {
                Element recordEless = (Element) iterss.next();
                String result = recordEless.elementTextTrim("result"); // 拿到body节点下的子节点result值
                System.out.println("result:" + result);
                Iterator itersElIterator = recordEless.elementIterator("form"); // 获取子节点body下的子节点form
                // 遍历Header节点下的Response节点
                while (itersElIterator.hasNext()) {
                    Element itemEle = (Element) itersElIterator.next();
                    String banlce = itemEle.elementTextTrim("banlce"); // 拿到body下的子节点form下的字节点banlce的值
                    String subID = itemEle.elementTextTrim("subID");
                    System.out.println("banlce:" + banlce);
                    System.out.println("subID:" + subID);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
