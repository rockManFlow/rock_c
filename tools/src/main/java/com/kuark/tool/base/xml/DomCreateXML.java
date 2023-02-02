package com.kuark.tool.base.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;

/**
 * org.w3c.dom
 * 创建一个xml文件
 */
public class DomCreateXML {
	public static void main(String[] args) throws Exception {
		//  实例化一个xml创建工厂类
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		//创建xml文件的解析器
		DocumentBuilder db = dbf.newDocumentBuilder();
		//获得document对象，等价于xml文件
		Document document = db.newDocument();

		//在xml中创建标签对象
		Element persons = document.createElement("persons");
		Element person = document.createElement("person");
		//给标签对象中添加属性和对应的值
		person.setAttribute("id", "1");
		//创建标签对象
		Element name = document.createElement("name");
		Element age = document.createElement("age");
		//在标签中间添加数据
		name.setTextContent("zhangsan");
		age.setTextContent("13");
		//一样
		Element person1 = document.createElement("person");
		person1.setAttribute("id", "2");
		Element name1 = document.createElement("name");
		Element age1 = document.createElement("age");
		name1.setTextContent("lisi");
		age1.setTextContent("18");
		//在父标签对象中添加子标签对象
		person1.appendChild(name1);
		person1.appendChild(age1);
		person.appendChild(name);
		person.appendChild(age);
		persons.appendChild(person);
		persons.appendChild(person1);
		//把最外部的标签添加到xml文档对象中
		document.appendChild(persons);

		//关键是设置一转换格式
		//创建xml文件的工厂类
		TransformerFactory tfs = TransformerFactory.newInstance();
		//删除xml文件的transformer类
		Transformer ts = tfs.newTransformer();
		//设置xml文件的编码格式
		ts.setOutputProperty(OutputKeys.ENCODING,"utf-8");
		// 输出的时候换行
		ts.setOutputProperty(OutputKeys.INDENT,"yes");

		//xml文件的DOM资源文件
		DOMSource ds = new DOMSource(document);
		//文件输出对象---即把DOM对象输出到哪个文件中
		StreamResult sr = new StreamResult(new FileOutputStream("e:/persons.xml"));
		//已设置好的格式来吧DOM对象输出到指定的文件中
		ts.transform(ds, sr);
	}
}
