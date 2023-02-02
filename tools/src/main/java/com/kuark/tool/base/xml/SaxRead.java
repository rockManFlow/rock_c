package com.kuark.tool.base.xml;

import com.kuark.tool.base.vo.Person;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.util.List;

public class SaxRead {
	public static void main(String[] args) throws Exception {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser parser = spf.newSAXParser();
		FileInputStream fis = new FileInputStream("e:/persons.xml");
		PersonHandler handler = new PersonHandler();
		parser.parse(fis, handler);
//		sendReq.print();
		List<Person> list = PersonHandler.list;
//		System.out.println("----------------------");
//		System.out.println(list.size());
		for(Person p :list){
			System.out.println(p);
		}
	}
}
