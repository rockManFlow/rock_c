package com.kuark.tool.advance.advance20201105util.excel;

import com.alibaba.excel.EasyExcel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rock
 * @detail
 * @date 2021/10/13 14:57
 */
public class ExcelMain {
    public static void main(String[] args) throws FileNotFoundException {
        List<ExcelVo> list=new ArrayList<>(2);
        ExcelVo v=new ExcelVo();
        v.setOrderNo("1111111");
        v.setReferId("2222222");
        v.setReferName("xiaohong");
        v.setReferType("K");
        v.setUserId("123456");
        v.setUserPhone("8888888");
        list.add(v);
        list.add(v);
        String path="E:\\excel.csv";
        FileOutputStream out=new FileOutputStream(path);
        EasyExcel.write(out, ExcelVo.class)
                .sheet("sheet0")
                .doWrite(list);
    }
}
