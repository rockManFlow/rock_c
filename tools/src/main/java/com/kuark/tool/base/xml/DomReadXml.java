package com.kuark.tool.base.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;

/**
 * org.w3c.dom
 * 读取一个指定的xml文件
 */
public class DomReadXml {
	public static void main(String[] args) throws Exception {
		//实例化一个xml创建工厂类
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		//创建xml文件的解析器
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		FileInputStream fis = new FileInputStream("e:/persons.xml");
		//把已存在的xml文件转换成document对象
		Document document = db.parse(fis);

		//默认返回xml文件的根节点。元素类Element
		Element persons = document.getDocumentElement();
		//获取根标签的所有子节点
		NodeList nodes = persons.getChildNodes();
		System.out.println("all length="+nodes.getLength());
		//遍历子节点
		for(int i =0 ; i < nodes.getLength();i++){
			//获得指定下标的节点(节点类)
			Node node = nodes.item(i);

			//获得该节点的标签名
			String nodeName = node.getNodeName();
			if("person".equals(nodeName)){
				// 拿到当前标签的所有属性，返回一个namedNodeMap
			/*	NamedNodeMap nodeMap = node.getAttributes();
				Node id = nodeMap.getNamedItem("id");
				System.out.println(id.getNodeValue());*/
				// node可能是文字内容、CDATA段、元素、属性等等,通过这个来判断节点类型
				if(node.getNodeType() == 1){//类型为该标签对象的属性，而不是标签中其他对象内容
					Element e = (Element)node;
					//通过名称获得属性值
					String id = e.getAttribute("id");
					System.out.println("id--->"+id);
				}
				NodeList nodes2 = node.getChildNodes();
				System.out.println("zi size="+nodes2.getLength());
				for(int j = 0 ; j <nodes2.getLength();j++){
						Node node2 = nodes2.item(j);
						String nodeName2 = node2.getNodeName();
						if("name".endsWith(nodeName2)){
							System.out.println("name-->"+node2.getTextContent());
						}
						if("age".equals(nodeName2)){
							System.out.println("age--->"+node2.getTextContent());
						}
				}
			}
		}
		System.out.println("=====================================");
		//获取指定标签名的的所有节点对象
		NodeList list = document.getElementsByTagName("name");
		for(int i = 0; i <list.getLength();i++){
			System.out.println("节点标签中的内容："+list.item(i).getTextContent());
		}
		/*try {
			System.out.println("id");
			//获取具有给定ID属性值的Element对象
			Element element = document.getElementById("1");
			System.out.println(element.getNodeName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
