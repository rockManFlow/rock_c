package com.kuark.tool.base.excel;

import com.kuark.tool.base.excel.vo.AdminOrderQueryVo;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rock
 * @detail
 * @date 2020/4/24 10:24
 */
public class ExcelMain {
    public static void main(String[] args) {
        //把实体信息转换到Excel表中，通过输出流输出到客户端
        HttpServletResponse resp=null;
        List<AdminOrderQueryVo> respList = new ArrayList<>(1);
        new ExcelPoiUtil().createSampleExcel(AdminOrderQueryVo.class, respList, resp,"filename");
    }
}
