package com.kuark.tool.base.excel;

import com.google.common.collect.Lists;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 * 类ExcelUtil.java的实现描述：excel 生成工具
 *  excel2007--可以导出单个sheet，最大可以到104万多
 */
public class XExcelPoiUtil {

    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet    = null;

    public XExcelPoiUtil() {
        this(new XSSFWorkbook());
    }

    public XExcelPoiUtil(XSSFWorkbook workbook) {
        this.workbook = workbook;
        this.sheet = workbook.createSheet();
    }

    public XExcelPoiUtil(XSSFWorkbook workbook, XSSFSheet sheet) {
        this.workbook = workbook;
        this.sheet = sheet;
    }

    /**
     * 创建没有行列合并的最简单的Excel
     * 
     * @param dataList 数据集合 title通过 {@link ExcelTitle} 指定
     * @param clazz 集合的数据类型
     * @throws Exception
     */
    public void createSampleExcel(Class<?> clazz, List<?> dataList, HttpServletResponse response, String fileName) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> columnHeaders = Lists.newArrayList();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ExcelTitle.class)) {
                ExcelTitle excelTitle = field.getAnnotation(ExcelTitle.class);
                columnHeaders.add(excelTitle.value());
            }
        }

        this.createColumnData(columnHeaders.toArray(new String[columnHeaders.size()]), dataList,response, fileName);
    }

    /**
     * 创建没有行列合并的最简单的Excel
     * 
     * @param columnHeader
     * @param dataList
     * @throws Exception
     */
    private void createColumnData(String[] columnHeader, List<?> dataList, HttpServletResponse response, String fileName) {
        int rowNO = 0;
        createColumnHeader(sheet, rowNO, 500, columnHeader);
        rowNO++;
        if (!dataList.isEmpty()) {
            String[][] columnData = ListToArrayUtil.listToArrayWay(dataList);
            sheet = createColumnData(sheet, rowNO, 400, columnData, 1000000);
        }
        exportExcelStream(response,fileName);
    }

    /**
     * 创建数据行
     *
     * @param sheet (创建sheet)
     * @param rowNO 报表的单行行号(创建第几行)
     * @param rowHeight 报表的单行行高
     * @param columnData 报表行中显示的数据
     * @param maxValue Excel显示的最大上限
     */
    private XSSFSheet createColumnData(XSSFSheet sheet, int rowNO, int rowHeight, String[][] columnData, int maxValue) {
        //xls一个sheet最大是65535，xlsx一个sheet最大是1048575
        maxValue = (maxValue < 1 || maxValue > 1048575) ? 1048575 : maxValue;
        int currRowNO = rowNO;
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        for (int numNO = currRowNO; numNO < columnData.length + currRowNO; numNO++) {
            if (numNO % maxValue == 0) {
                sheet = workbook.createSheet();
                rowNO = 0;
            }
            createColumnDataDesc(sheet, numNO, rowNO, currRowNO, rowHeight, columnData, cellStyle);
            rowNO++;
        }
        return sheet;
    }

    /**
     * 创建数据行
     *
     * @param sheet (创建sheet)
     * @param numNO 序列号
     * @param rowNO 报表的单行行号(创建第几行)
     * @param currRowNO 初始行号
     * @param rowHeight 报表的单行行高
     * @param columnData 报表行中显示的数据
     */
    private void createColumnDataDesc(XSSFSheet sheet, int numNO, int rowNO, int currRowNO, int rowHeight,
                                      String[][] columnData, XSSFCellStyle cellStyle) {
        if (columnData == null || columnData.length < 1) {
            System.out.println(this.getClass().getName() + " --> Excel columnData is null");
        }
        XSSFRow row = sheet.createRow(rowNO);
        row.setHeight((short) (rowHeight < 1 ? 400 : rowHeight)); // 设置行高

        if (cellStyle != null) {
            cellStyle.setAlignment(HorizontalAlignment.CENTER); // 指定单元格居中对齐
        } else {
            cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER); // 指定单元格居中对齐
        }
        XSSFCell cell = null;
        if (numNO - currRowNO < 0 || columnData[numNO - currRowNO] == null) {
            System.out.println(this.getClass().getName() + " --> Excel columnData is null");
            return;
        }
        for (int i = 0; i < columnData[numNO - currRowNO].length; i++) {
            sheet.setColumnWidth(i, 20 * 256); // 设置列宽，20个字符宽度。宽度参数为1/256，故乘以256
            cell = row.createCell(i);
            cell.setCellType(CellType.STRING);
            if (cellStyle != null) {
                cell.setCellStyle(cellStyle);
            }
            cell.setCellValue(new XSSFRichTextString(columnData[numNO - currRowNO][i]));
        }
    }


    /**
     * 设置报表列头 OK
     * 
     * @param sheet (创建sheet)
     * @param rowNO 报表的单行行号(创建第几行)
     * @param rowHeight 报表的单行行高
     * @param columnHeader 报表行中显示的字符
     */
    private void createColumnHeader(XSSFSheet sheet, int rowNO, int rowHeight, String[] columnHeader) {
        createColumnHeader(sheet, rowNO, rowHeight, columnHeader, -1, null, null);
    }

    /**
     * 设置报表列头
     * @param sheet (创建sheet)
     * @param rowNO 报表的单行行号(创建第几行)
     * @param rowHeight 报表的单行行高
     * @param columnHeader 报表行中显示的字符
     * @param fontSize 字体的大小 (字体大小 默认 200)
     * @param fontWeight 报表表头显示的字符
     * @param align 字体水平位置 (center中间 right右 left左)
     */
    private void createColumnHeader(XSSFSheet sheet, int rowNO, int rowHeight, String[] columnHeader, int fontSize,
                                   String fontWeight, String align) {
        if (columnHeader == null || columnHeader.length < 1) {
            System.out.println(this.getClass().getName() + " --> Excel columnHeader is null");
            return;
        }
        XSSFRow row = sheet.createRow(rowNO);
        row.setHeight((short) rowHeight);

        XSSFCellStyle cellStyle = createCellFontStyle(workbook, fontSize, fontWeight, align);
        if (cellStyle != null) {
            // 设置单元格背景色
            cellStyle.setFillForegroundColor(HSSFColorPredefined.GREY_25_PERCENT.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }

        XSSFCell cell = null;
        for (int i = 0; i < columnHeader.length; i++) {
            sheet.setColumnWidth(i, 20 * 256); // 设置列宽，20个字符宽度。宽度参数为1/256，故乘以256
            cell = row.createCell(i);
            cell.setCellType(CellType.STRING);
            if (cellStyle != null) {
                cell.setCellStyle(cellStyle);
            }
            cell.setCellValue(new XSSFRichTextString(columnHeader[i]));
        }
    }

    /**
     * 设置字体样式 (字体为宋体 ，上下居中对齐，可设置左右对齐，字体粗细，字体大小 )
     * OK
     * @param workbook 如果为空 则没有样式
     * @param fontSize 字体大小 默认 200
     * @param fontWeight 字体粗细 ( 值为bold 为加粗)
     * @param align 字体水平位置 (center中间 right右 left左)
     */
    private XSSFCellStyle createCellFontStyle(XSSFWorkbook workbook, int fontSize, String fontWeight, String align) {
        if (workbook == null) {
            System.out.println("Excel XSSFWorkbook FontStyle is not set");
            return null;
        }

        XSSFCellStyle cellStyle = workbook.createCellStyle();

        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 指定单元格居中对齐
        if (align != null && align.equalsIgnoreCase("left")) {
            cellStyle.setAlignment(HorizontalAlignment.LEFT); // 指定单元格居中对齐
        }
        if (align != null && align.equalsIgnoreCase("right")) {
            cellStyle.setAlignment(HorizontalAlignment.RIGHT); // 指定单元格居中对齐
        }

        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 指定单元格垂直居中对齐
        cellStyle.setWrapText(true);// 指定单元格自动换行

        // 单元格字体
        XSSFFont font = workbook.createFont();
        font.setBold(fontWeight != null && fontWeight.equalsIgnoreCase("normal"));
        font.setFontName("宋体");
        font.setFontHeight((short) (fontSize < 1 ? 200 : fontSize));
        cellStyle.setFont(font);

        return cellStyle;
    }

    /**
     * 通过流输出Excel
     * @param response
     * @param fileName
     */
    private void exportExcelStream(HttpServletResponse response, String fileName){
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            response.setContentType("application/x-download");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xlsx");
            workbook.write(os);
            os.flush();
        }catch (Exception e) {
            System.err.println("exportExcelStream exception");
        }finally {
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    System.err.println("exportExcelStream close exception");
                }
            }
        }
    }
}
