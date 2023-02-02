package com.kuark.tool.base.excel;

import com.kuark.tool.base.database.QueryMethod;
import com.kuark.tool.base.vo.ControlPersonPojo;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by cc on 2016/4/8.
 * TODO 使用jxl包把数据库中查出的数据写到excel表中
 */
public class CreateJxlExcel {
    //Excel工作簿路径
    private static String targetFile="D://book.xls";
    //工作表名
    private static String sheet="sheet1";
    //把查出来的数据库中的数据写到Excel表格中
    public static void OutputDataToXLS() throws Exception {
        try {
            //查询数据库中所有的数据
            List<ControlPersonPojo> list=new ArrayList<ControlPersonPojo>();
            List<HashMap<String,Object>> listMap= QueryMethod.queryData();
            for(HashMap<String,Object> hashMap:listMap){
                ControlPersonPojo conPerPojo=new ControlPersonPojo();
                Set<String> set=hashMap.keySet();
                for(String name:set){
                    if("id".equals(name)){
                        conPerPojo.setId((Integer) hashMap.get(name));
                    }if("person_name".equals(name)){
                        conPerPojo.setPersonName((String) hashMap.get(name));
                    }if("person_pinyin".equals(name)){
                        conPerPojo.setPersonPinyin((String) hashMap.get(name));
                    }if("department".equals(name)){
                        conPerPojo.setDepartment((String) hashMap.get(name));
                    }
                }
                list.add(conPerPojo);
            }
            //获取类的所有属性
            if(list.isEmpty()){
                return;
            }
            WritableWorkbook wwb = null;
            // 创建可写入的Excel工作簿
            File file=new File(targetFile);
            if (!file.exists()) {
                file.createNewFile();
            }
            //以fileName为文件名来创建一个Workbook工作簿
            wwb = Workbook.createWorkbook(file);
            // 在工作簿中创建工作表
            WritableSheet ws = wwb.createSheet(sheet, 0);
            Class cs=list.get(0).getClass();
            Field[] fields=cs.getDeclaredFields();
            for(int f=0;f<fields.length;f++){
                //要插入到的Excel表格的行号，默认从0开始==Label文本单元格
                Label label= new Label(f, 0, fields[f].getName());//表示第几列，第几行，内容
                //把单元格加入到工作表中
                ws.addCell(label);//以下标为标准
            }
            //通过反射获取不同返回对象的数据
            Method[] methods=cs.getDeclaredMethods();
            for (int i = 0; i < list.size(); i++) {
                for(int f=0;f<methods.length;f++){
                    if(methods[f].getName().contains("get")) {
                        for(int k=0;k<fields.length;k++){
                            //获取属性名
                            String fieldName=fields[k].getName();
                            //把属性第一个字符变为大写
                            String firstC=fieldName.substring(0,1).toUpperCase();
                            String subfix=fieldName.substring(1,fieldName.length());
                            //组装方法名
                            String getMethodName="get"+firstC+subfix;
                            if(methods[f].getName().contains(getMethodName)){
                                Label label = new Label(k, i + 1, methods[f].invoke(list.get(i)) + "");
                                ws.addCell(label);
                                break;
                            }
                        }
                    }
                }
            }

            //写进文档
            wwb.write();
            // 关闭Excel工作簿对象
            wwb.close();

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        System.out.println("write finish");
    }
    public static void main(String[] args) {
        try {
            OutputDataToXLS();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
