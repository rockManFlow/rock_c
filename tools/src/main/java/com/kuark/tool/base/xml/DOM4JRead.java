package com.kuark.tool.base.xml;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.dom4j.tree.DefaultAttribute;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;

/**
 *TODO 通过这个包org.dom4j来读取xml文件比org.w3c.dom好用
 * 只能读取特定指定样式的xml文件
 */
public class DOM4JRead {
	public static void main(String[] args) throws Exception {
//		SAXReader，DOMReader两个类都是读取xml的，使用一样
		DOMReader dr = new DOMReader();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		//解析xml文件的解析器
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		FileInputStream fis = new FileInputStream("e:/persons.xml");
		Document d = db.parse(fis);
		//或者通过document = dr.read(new File(filename));读取xml
		org.dom4j.Document document = dr.read(d);
		//获取根节点对象
		Element root = document.getRootElement();
//		//获取标签名
//		String rootName1=root.getName();
//		System.out.println("rootName:"+rootName1);
		//获取该节点对应的类型名
//		String rootName=root.getNodeTypeName();
//		System.out.println("rootName:"+rootName);
		//返回这个根节点包含的所有孩子节点,只包括孩子节点不包括孙子节点
		List<Element> children = root.elements();
		
//		/*迭代方式遍历
//		Iterator iterator = root.attributeIterator();
//		while(iterator.hasNext()){
//			System.out.println(iterator.next());
//		}*/
		
		System.out.println("节点sum size:"+children.size());
		for(Element e:children){
			//返回该标签对象包含的所有属性
			List<Attribute> attributes = e.attributes();
			Iterator<Attribute> iterator = e.attributeIterator();
			while(iterator.hasNext()){
				Attribute attribute = iterator.next();
				System.out.println(attribute.getName()+"-->"+attribute.getValue());
				//修改节点属性
//				if (attribute.getValue().equals("yes")){
//					attribute.setValue("no");
//				}
			}
			//再获取该标签中的所有子标签
			List<Element> chiledList=e.elements();
			if(chiledList.isEmpty()){
				continue;
			}
			System.out.println("=================");
			for(Element ee:chiledList){
				System.out.println(ee.getName()+"-->"+ee.getText());
			}
			System.out.println("=================");
//			/*获取指定名字的属性对象
//			Attribute attribute = e.attribute("id");
//			System.out.println(attribute.getName()+"---"+ attribute.getValue());
//			for(Attribute a: attributes){
//				System.out.println(a.getName()+"------------"+ a.getValue());
//			}*/
//			/*List<Element> elements = e.elements();
//			for(Element ele: elements){
//				System.out.println(ele.getName()+"----"+ ele.getText());
//			}*/
		}
	}
}
