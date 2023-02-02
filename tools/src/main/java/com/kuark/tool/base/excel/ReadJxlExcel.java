package com.kuark.tool.base.excel;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by cc on 2016/5/10.
 * TODO 使用jxl读取excel文档的信息
 */
public class ReadJxlExcel {
    /**
     * 读取xls文件内容
     *
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static String xls2String(File file) {
        String result = "";
        try {
            FileInputStream fis = new FileInputStream(file);
            StringBuilder sb = new StringBuilder();
            //获取工作簿对象
            jxl.Workbook rwb = Workbook.getWorkbook(fis);
            //获取所有的工作表
            Sheet[] sheet = rwb.getSheets();
            for (int i = 0; i < sheet.length; i++) {
                Sheet rs = rwb.getSheet(i);
                for (int j = 0; j < rs.getRows(); j++) {
                    //获取第一行的所有
                    Cell[] cells = rs.getRow(j);
                    System.out.println("cell size:"+cells.length);
                    System.out.println("cell content:");
                    for (int k = 0; k < cells.length; k++) {
                        System.out.print("=="+cells[k].getContents());//间隔的也会输出
                        sb.append(cells[k].getContents());
                    }
                    System.out.println();
                }
            }
            fis.close();
            result += sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws IOException, BiffException {
        File file = new File("D:/延迟.xls");
        readExcel(file);
    }

    /**
     *读取清算excel表的订单号
     * @param files
     * @throws IOException
     * @throws BiffException
     */
    public static void readExcel(File files) throws IOException, BiffException {
        FileInputStream fis = new FileInputStream(files);
        jxl.Workbook rwb = Workbook.getWorkbook(fis);
        Sheet[] sheets= rwb.getSheets();
        for (int i = 0; i < sheets.length; i++) {
            StringBuilder sd=new StringBuilder();
            Sheet rs = rwb.getSheet(i);
            Cell[] column = rs.getColumn(0);//这是第几列中的数据
            System.out.println("length="+column.length);
            int index=0;
            for(int j=1;j<column.length;j++){
                Cell col=column[j];
                if(!"".equals(col.getContents())) {
                    if(index==0){
                        sd.append("'"+col.getContents()+"'");
                        ++index;
                    }else{
                        sd.append(",'"+col.getContents()+"'");
                    }
                }
            }
            System.out.println("拼接的sd="+sd.toString());
        }
        fis.close();
    }
    /*
    * 支持方法：
    *    Sheet[] sheet= workbook.getSheets();获取所有的工作表
    *   sheet.getName()    返回Sheet标题
    *   sheet.getColumns() Sheet页的总列数
    *   sheet.getColumn(0)Sheet页的某一列的数组
    *   sheet.getRows()Sheet页的总行数
    *   sheet.getRow(0)Sheet页的某一行的数组
    *
    *   返回第一行,第一列的值  (0  (第一个代表列)    ,0  (第二个代表行))
        Cell cell00=sheet.getCell(0, 0);
        System.out.println(cell00.getType());  //获得代表类的类型 (返回类的类型)
        System.out.println(cell00.getContents());//获得代表类的类型 (返回string)
    * */
}
