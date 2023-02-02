package com.kuark.tool.model.mybatis;

import com.kuark.tool.model.mybatis.dao.interfaces.ExcelInter;
import com.kuark.tool.model.mybatis.entitys.ExcelEntity;
import com.kuark.tool.model.mybatis.entitys.TiggerEntity;
import com.kuark.tool.base.http.URLConnection;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by caoqingyuan on 2017/7/3.
 */
public class MybatisMain {
    public static void main(String[] s) throws Exception {
        String url="http://localhost:8080/mod/testMybatis.do";
        byte[] bytes = URLConnection.postBinResource(url, "", "UTF-8", 30);
        System.out.println("response:"+new String(bytes,"UTF-8"));
//        ApplicationContext ctx=null;
//        ctx=new ClassPathXmlApplicationContext("springmvc.xml");
//        ExcelInter excelInter=(ExcelInter) ctx.getBean("excelInter");
//        ExcelEntity entity = excelInter.queryById(4);
//        System.out.println("entity:"+entity);
//        ExcelService service=new ExcelService();

        System.out.println("done");
    }
    private SqlSessionFactory sessionFactory;
    private static SqlSession session;
    private ExcelInter mapper;

    @Before
    public void setup() {
        String resource = "mybatis-config.xml";
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(is);
            session = sessionFactory.openSession();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void getObjects() {
        // 分页查询
        // 获取 映射 借口 对象 ;
        mapper = session.getMapper(ExcelInter.class);
//        ExcelEntity entity = mapper.queryById(4);
//        ExcelEntity entity = mapper.queryByName("xiaohei");
//        List<ExcelEntity> list= mapper.queryAll();
//        for(ExcelEntity entity:list) {
//            System.out.println("name:" + entity.getName());
//            System.out.println("id:" + entity.getId());
//            System.out.println("age:" + entity.getAge());
//        }
//        ExcelEntity entity=new ExcelEntity();
//        entity.setAge(20);
//        entity.setName("xiaolan");
//        ExcelEntity condition=new ExcelEntity();
//        condition.setId(2);
//        mapper.insertExcel(entity);
//        mapper.updateExcel(entity,condition);
//        List<ExcelEntity> list=new ArrayList<ExcelEntity>();
//        ExcelEntity entity = new ExcelEntity();
//        for(int i=0;i<4;i++) {
//            entity.setAge(20+i);
//            entity.setName("xiaolan:"+i);
//            list.add(entity);
//        }
//        mapper.insertExcelLists(list);
//        List<ExcelEntity> list = mapper.queryByAge(23);
//        for(ExcelEntity entity:list){
//            System.out.println("id:"+entity.getId());
//        }
//        ExcelEntity entity=new ExcelEntity();
//        entity.setName("xiaolan:3");
//        entity.setAge(23);
//        List<Map<String, String>> maps = mapper.queryByNameAndAge(entity);
//        for(Map map:maps){
//            Set<String> keys=map.keySet();
//            for(String key:keys){
//                System.out.println("key:"+key+"||value:"+map.get(key));
//            }
//        }
//        session.commit();
        ExcelEntity entity = mapper.queryTigger(4);
        TiggerEntity tigger = entity.getTigger();
        System.out.println("name:"+tigger.getTiggerName());
        System.out.println("id:"+tigger);
        System.out.println("[]"+entity);
        System.out.println("done");
    }

    @After
    public void result() {
        if (sessionFactory != null) {
            sessionFactory = null;
        }
        if (session != null) {
            session = null;

        }
    }
}
