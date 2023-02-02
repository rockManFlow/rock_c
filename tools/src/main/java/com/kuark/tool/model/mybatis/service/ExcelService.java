package com.kuark.tool.model.mybatis.service;

import com.kuark.tool.model.mybatis.dao.interfaces.ExcelInter;
import com.kuark.tool.model.mybatis.entitys.ExcelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by caoqingyuan on 2017/7/3.
 */
@Service
public class ExcelService {
    //通过了类型注入，自动注入不能注入，不让其抛出异常
    @Autowired(required = false)
    private ExcelInter excel;
    public ExcelEntity queryExcelByName(String name){
        System.out.println("ExcelService queryExcelByName");
        ExcelEntity excelEntity = excel.queryByName(name);
        System.out.println("excelEntity:"+excelEntity);
        return excelEntity;
    }
    public ExcelEntity queryExcelById(Integer id){
        ExcelEntity excelEntity = excel.queryById(id);
        System.out.println("queryExcel:"+excelEntity);
        return excelEntity;
    }
}
