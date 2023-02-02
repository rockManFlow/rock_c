package com.kuark.tool.advance.advance20201105util.excel;

import com.alibaba.excel.EasyExcel;
import com.kuark.tool.advance.advance20201105util.excel.advance1.EasyExcelBizAdvanceService;
import com.kuark.tool.advance.advance20201105util.excel.advance1.ExcelRespContext;
import com.kuark.tool.advance.advance20201105util.excel.advance1.ExcelUploadAdvanceListener;
import com.kuark.tool.advance.advance20201105util.excel.base.EasyExcelBizService;
import com.kuark.tool.advance.advance20201105util.excel.base.ExcelUploadListener;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Java解析、生成Excel比较有名的框架有Apache poi、jxl。但他们都存在一个严重的问题就是非常的耗内存，
 * poi有一套SAX模式的API可以一定程度的解决一些内存溢出的问题，但POI还是有一些缺陷，
 * 比如07版Excel解压缩以及解压后存储都是在内存中完成的，内存消耗依然很大。
 * easyexcel重写了poi对07版Excel的解析，能够原本一个3M的excel用POI sax依然需要100M左右内存降低到几M，
 * 并且再大的excel不会出现内存溢出，03版依赖POI的sax模式。在上层做了模型转换的封装，让使用者更加简单方便
 *
 * 具体其他用法看源码示例
 */
@Slf4j
public class EasyExcelUtil {

    /**
     * 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
     * @param dataList
     * @param dataClass
     * @param fileName
     * @param resp
     * @param <T>
     */
    public static <T> void simpleWrite(List<T> dataList, Class<T> dataClass, String fileName, HttpServletResponse resp){
        try {
            EasyExcel.write(buildResponse(resp, fileName).getOutputStream(), dataClass)
                    .sheet("sheet0")
                    .doWrite(dataList);
        }catch (IOException e) {
            log.info("simpleWrite exception",e);
        }
    }

    /**
     * 通过流输出Excel
     * @param response
     * @param fileName
     */
    private static HttpServletResponse buildResponse(HttpServletResponse response, String fileName) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xlsx");
       return response;
    }

    public static <T> void simpleRead(Class<T> dataClass, InputStream inputStream, EasyExcelBizService<T> bizService){
        EasyExcel.read(inputStream,dataClass,new ExcelUploadListener<T>(bizService)).headRowNumber(1).sheet().doRead();
    }

    /**
     * 带返回信息的--读取Excel文件
     */
    public static <T,S> void simpleRead(Class<T> dataClass, InputStream inputStream, EasyExcelBizAdvanceService<T,S> bizService, ExcelRespContext<S> respContext, Integer headRowNumber){
        EasyExcel.read(inputStream,dataClass,new ExcelUploadAdvanceListener<T,S>(bizService,respContext)).headRowNumber(headRowNumber).sheet().doRead();
    }
}
