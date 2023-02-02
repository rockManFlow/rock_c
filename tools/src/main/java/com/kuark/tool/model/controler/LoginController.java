package com.kuark.tool.model.controler;

import com.kuark.tool.model.mybatis.dao.interfaces.ExcelInter;
import com.kuark.tool.model.mybatis.entitys.ExcelEntity;
import com.kuark.tool.model.mybatis.entitys.SpringEntity;
import com.kuark.tool.model.oper.ThreadLocalVar;
import com.kuark.tool.model.test.shiwu.MainPojo;
import com.kuark.tool.model.test.shiwu.ServiceTest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by caoqingyuan on 2016/10/20.
 */
@RestController
public class LoginController {
    private static final Logger logger= Logger.getLogger(LoginController.class);
    @Autowired(required = false)
    private ExcelInter service;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private SpringEntity springss;
    @Autowired
    private Tests t;

    @RequestMapping("sss.do")
    public String sss(){
        logger.info("SpringEntity:"+springss);
        new Tests().service();
        logger.info("===============");
        t.service();
        return "index";
    }

    @ResponseBody
    @RequestMapping("testMybatis.do")
    public String testMybatis(){
        System.out.println("transactionTemplate:"+transactionTemplate);
        // 开始事务，如果出现状况则回滚
        //mybatis编程式事务管理
        Object o=transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                try{
                    System.out.println("进入事务管理");
                    ExcelEntity entity=new ExcelEntity();
                    entity.setName("hhh111");
                    entity.setAge(101);
                    service.insertExcel(entity);
                    throw new Exception("自定义抛出异常");
                }catch (Exception e){
                    e.printStackTrace();
                    transactionStatus.setRollbackOnly();
                    return "exception Rollback";
                }
            }
        });
        ExcelEntity entity=new ExcelEntity();
        entity.setId(7);
        service.deleteExcel(entity);
        return (String)o;
    }



    @RequestMapping("login.do")
    public ModelAndView login(String username, String password){
        logger.info("username="+username);
        logger.info("password="+password);
        return new ModelAndView("index");
    }
    @RequestMapping("verify.do")
    public void verify() throws Exception {
        System.out.println("verify start");
//        List<MainPojo> pojos = ServiceTest.select1(ThreadLocalVar.getSourceCon(),2,1);
//        for(MainPojo m:pojos){
//            System.out.println("name="+m.getName()+"age="+m.getAge());
//        }
        System.out.println("verify end");
    }
}
