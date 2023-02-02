package com.kuark.tool.base.xml;

import com.kuark.tool.base.database.QueryMethod;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by cc on 2016/5/9.
 * TODO 使用dom4j创建xml文件，dom基于文件树解析xml的
 * 把数据库中查出的数据输出到xml文件中
 */
public class DOM4JCreate {
    public void createXML() throws SQLException {
        //新创建一个document对象
        Document doc=DOM4JUtil.createDomFJ();
        //添加注释
        doc.addComment("以utf-8的编码");
        //创建根目录标签
        Element root=doc.addElement("Table");
        /*格式化输出*/
        org.dom4j.io.OutputFormat format=org.dom4j.io.OutputFormat.createPrettyPrint();//紧缩
        format.setEncoding("utf-8");   //设置utf-8编码
        Element head=root.addElement("Head");
        //TODO 从数据库中查取数据
        List<HashMap<String,Object>> listMap= QueryMethod.queryData();
        for(HashMap<String,Object> hashMap:listMap){
            Element row=head.addElement("Row");
            Set<String> set=hashMap.keySet();//通过键值对来获取到指定字段的名
            for(String tagName:set){
                Element trancod=row.addElement(tagName);
                trancod.setText(hashMap.get(tagName).toString());
            }
        }
        try {
            // TODO 把创建的dom对象写到xml文件中去
            XMLWriter writer=new XMLWriter(new OutputStreamWriter(new FileOutputStream("e:/xml/table.xml")),format);
            writer.write(doc);
            writer.close();
            System.out.println("finish");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] s) throws SQLException {
        DOM4JCreate dom4JCreate=new DOM4JCreate();
        dom4JCreate.createXML();
    }
}
