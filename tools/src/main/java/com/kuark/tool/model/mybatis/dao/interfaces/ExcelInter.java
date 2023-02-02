package com.kuark.tool.model.mybatis.dao.interfaces;

import com.kuark.tool.model.mybatis.entitys.ExcelEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 定义数据库操作接口
 * Created by caoqingyuan on 2017/6/30.
 */
public interface ExcelInter {
    public int a=12;
    public ExcelEntity queryById(Integer id);
    public ExcelEntity queryByName(String name);
    public List<ExcelEntity> queryAll();
    public void insertExcel(ExcelEntity entity);
    public void updateExcel(@Param("val") ExcelEntity value, @Param("condition") ExcelEntity condition);
    public void deleteExcel(ExcelEntity entity);

    //插入一个集合数据
    public void insertExcelLists(List<ExcelEntity> lists);
    //
    public List<ExcelEntity> queryByAge(Integer age);
    //返回map集合=key为列名  value为列对应的值
    public List<Map<String, String>> queryByNameAndAge(ExcelEntity entity);
    //
    public ExcelEntity queryTigger(Integer id);
}
