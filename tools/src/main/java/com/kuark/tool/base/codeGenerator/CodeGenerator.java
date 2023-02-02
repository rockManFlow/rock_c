//package com.kuark.archive.codeGenerator;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import com.cxsoft.base.TableForm;
//import com.cxsoft.common.DBConnection;
//import com.cxsoft.common.DBFactory;
//import com.cxsoft.tool.DBTool;
//import com.cxsoft.tool.FormatTool;
//import com.cxsoft.tool.StringUtil;
///**
// * Created by cc on 2016/5/25.
// */
//public class CodeGenerator {
//    private static final Log log = LogFactory.getLog(CodeGenerator.class);
//
//    /**
//     * 引擎入口
//     * @param tableForm
//     * @throws Exception
//     */
//    public static void engineEntry(TableForm tableForm) throws Exception{
//        String basePath = StringUtil.replaceSlash(tableForm.getBasePath());
//        tableForm.setBasePath(basePath);
//        if(tableForm.isBeanBol()){
//            generateBean(tableForm);
//        }
//        if(tableForm.isServiceBol()){
//            generateService(tableForm);
//        }
//        if(tableForm.isJspBol()){
//            generateJsp(tableForm);
//        }
//    }
//
//    /**
//     * 生成Bean
//     * @param tableForm
//     * @throws Exception
//     */
//    private static void generateBean(TableForm tableForm) throws Exception{
//        String writeFilePath = tableForm.getStorePath();
//        String tableName = tableForm.getSelecedTable();
//        String objectName = StringUtil.initialStrToUpper(tableName);
//        objectName = StringUtil.convertUnderLine(objectName);
//        String packageName =  StringUtil.addPoint(tableForm.getBasePath(),"model;");
//        String fileName = StringUtil.addPoint(objectName,"java");
//        tableForm.setPackageName(packageName);
//        packageName = StringUtil.convertPoint(packageName);
//        tableForm.setSelecedTable(tableName);
//        tableForm.setObjectName(objectName);
//        tableForm.setFileName(fileName);
//        writeFilePath +="/"+ packageName+"/"+fileName;
//        tableForm.setWriteFilePath(writeFilePath);
//        readAndWriteFile("../codeBuilder/template/Bean.temp",tableForm);
//    }
//
//    /**
//     * 生成Service及其实现类
//     * @param tableForm
//     * @throws Exception
//     */
//    private static void generateService(TableForm tableForm) throws Exception{
//        String writeFilePath = tableForm.getStorePath();
//        String tableName = tableForm.getSelecedTable();
//        String objectName = StringUtil.initialStrToUpper(tableName);
//        objectName = StringUtil.convertUnderLine(objectName);
//        String packageName = StringUtil.addPoint(tableForm.getBasePath(),"service;");//tableForm.getBasePath()+".service;";
//        String fileName = "I"+objectName+"Service.java";
////      String rootPath = tableForm.getBasePath();
//        tableForm.setPackageName(packageName);
//        packageName = StringUtil.convertPoint(packageName);
//        tableForm.setSelecedTable(tableName);
//        tableForm.setObjectName(objectName);
//        tableForm.setFileName(fileName);
//        writeFilePath += "/"+packageName+"/"+fileName;
//        tableForm.setWriteFilePath(writeFilePath);
//        readAndWriteFile("../codeBuilder/template/Service.temp",tableForm);
//        {
//            String packageNameImple = StringUtil.addPoint(tableForm.getBasePath(),"service.imple;");//tableForm.getBasePath()+".service.imple;";
//            fileName = objectName + "ServiceImple.java";
//            tableForm.setPackageName(packageNameImple);
//            packageNameImple = StringUtil.convertPoint(packageNameImple);
//            tableForm.setSelecedTable(tableName);
//            tableForm.setFileName(fileName);
//            String writeFilePathImple = tableForm.getStorePath() +"/"+ packageNameImple+"/"+fileName;
//            tableForm.setWriteFilePath(writeFilePathImple);
//            readAndWriteFile("../codeBuilder/template/ServiceImple.temp",tableForm);
//        }
//    }
//
//    /**
//     * 生成Jsp页面
//     * @param tableForm
//     * @throws Exception
//     */
//    private static void generateJsp(TableForm tableForm) throws Exception{
//        String writeFilePath = tableForm.getStorePath();
//        String tableName = tableForm.getSelecedTable();
//        String objectName = StringUtil.initialStrToUpper(tableName);
//        objectName = StringUtil.convertUnderLine(objectName);
//        String basePath = tableForm.getStorePath() ;
//        String fileName = "/"+objectName.toLowerCase()+"/"+"list.jsp";
//        tableForm.setSelecedTable(tableName);
//        tableForm.setObjectName(objectName);
//        tableForm.setFileName(fileName);
//        writeFilePath = basePath+fileName;
//        tableForm.setWriteFilePath(writeFilePath);
//        readAndWriteFile("../codeBuilder/template/list.temp",tableForm);
//    }
//
//    /**
//     * 根据不同模版生成所需文件
//     * @param readFilePath
//     * @param tableForm
//     * @throws Exception
//     */
//    private static void readAndWriteFile(String readFilePath,TableForm tableForm) throws Exception{
//        try {
//            FileReader fr = new FileReader(readFilePath);// 创建FileReader对象，用来读取字符流
//            BufferedReader br = new BufferedReader(fr); // 缓冲指定文件的输入
//            String writeFilePath = tableForm.getWriteFilePath();
//            if(createFile(writeFilePath)){
//                FileWriter fw = new FileWriter(writeFilePath);// 创建FileWriter对象，用来写入字符流
//                BufferedWriter bw = new BufferedWriter(fw); // 将缓冲对文件的输出
//                String myreadline; // 定义一个String类型的变量,用来每次读取一行
//                while (br.ready()) {
//                    myreadline = br.readLine();// 读取一行
//                    if(tableForm.isBeanBol()){
//                        if(!"".equals(myreadline)) myreadline = filteBeanFile(myreadline,tableForm);
//                    }
//                    if(tableForm.isServiceBol()){
//                        if(!"".equals(myreadline)) myreadline = filteServiceFile(myreadline,tableForm);
//                    }
//                    if(tableForm.isJspBol()){
//                        if(!"".equals(myreadline)) myreadline = filteJspFile(myreadline,tableForm);
//                    }
//                    bw.write(myreadline); // 写入文件
//                    bw.newLine();
//                    log.info(myreadline);// 在屏幕上输出
//                }
//                bw.flush(); // 刷新该流的缓冲
//                bw.close();
//                fw.close();
//            }
//            br.close();
//            fr.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new Exception();
//        }
//    }
//
//    /**
//     * 过滤Service文件
//     * @param readLine
//     * @param tableForm
//     * @return
//     */
//    private static String filteServiceFile(String readLine,TableForm tableForm){
//        return filterCommon(readLine,tableForm);
//    }
//
//    /**
//     * 过滤Bean文件
//     * @param readLine
//     * @param tableForm
//     * @return
//     * @throws Exception
//     */
//    private static String filteBeanFile(String readLine,TableForm tableForm) throws Exception{
//        readLine = filterCommon(readLine,tableForm);
//        Object columns[] = tableForm.getRightAllValues();
//        StringBuffer sb = new StringBuffer();
//        if(readLine.contains("@fieldDeclare")){
//            for (int i = 0; i < columns.length; i++) {
//                String field = "";
//                String fieldDeclare = getField(columns[i].toString(),tableForm.getModel().getDBtype());
//                field += readLine.replaceAll("@fieldDeclare",fieldDeclare);
//                sb.append(field);
//            }
//            return sb.toString();
//        }else if(readLine.contains("@method")){
//            for (int i = 0; i < columns.length; i++) {
//                String columnType = columns[i].toString().split("-")[0];
//                String fieldType = translateToJavaType(tableForm.getModel().getDBtype(),columnType);
//                String fieldName = columns[i].toString().split("-")[1].toLowerCase();
//                StringBuffer method = new StringBuffer();
//                method.append(readLine.replaceAll("@method",getMethod(tableForm,fieldType,fieldName)+setMethod(tableForm.getObjectName(),fieldType,fieldName)));
//                sb.append(method);
//            }
//            return sb.toString();
//        }else{
//            return readLine;
//        }
//    }
//
//
//    /**
//     * 过滤Jsp文件
//     * @param readLine
//     * @param tableForm
//     * @return
//     * @throws SQLException
//     */
//    private static String filteJspFile(String readLine,TableForm tableForm) throws SQLException{
//        readLine = filterCommon(readLine,tableForm);
//        Object columns[] = tableForm.getRightAllValues();
//        StringBuffer sb = new StringBuffer();
//        String primaryColumns = "" ;
//        if(readLine.contains("@columns")){
//            for (int i = 0; i < columns.length; i++) {
//                String fieldDeclare = getColumns(columns[i].toString(),columns.length);
//                StringBuffer fields = new StringBuffer();
//                fields.append(readLine.replaceAll("@columns",fieldDeclare));
//                sb.append(fields);
//            }
//            return sb.toString();
//        }else if(readLine.contains("@formatter")){
//            for (int i = 0; i < columns.length; i++) {
//                DBFactory db = new DBFactory();
//                // 获取数据库连接
//                Connection conn = db.getDBConnectionInstance(tableForm.getModel()).getConnection();
//                String fieldName = columns[i].toString().split("-")[1].toLowerCase();
//                boolean isPrimary = DBTool.isPrimaryKey(conn, tableForm.getSelecedTable(), fieldName);
//                if(isPrimary){
//                    primaryColumns = fieldName;
//                    break;
//                }
//            }
//            readLine = readLine.replaceAll("@formatter",getFormatter(primaryColumns));
//            return readLine;
//        }else{
//            return readLine;
//        }
//    }
//
//    /**
//     * 获取操作相关
//     * @param sId
//     * @return
//     */
//    private static String getFormatter(String sId){
//        StringBuffer sb = new StringBuffer();
//        String t11 = "\t\t\t\t\t\t\t\t\t\t\t\t\t";
//        sb.append("var editId = \"editBtn"+sId+ ";\r\n");
//        sb.append(t11+"var view = '<a href=\"#\" mce_href=\"#\" onclick=\"view(\'"+sId+"\')\">查看</a> ';\r\n");
//        sb.append(t11+"var edit = '<a href=\"javascript:showUpdate('"+sId+"');\" id=\"'+editId+'\">编辑</a> ';\r\n");
//        sb.append(t11+"var dele = '<a href=\"javascript:doDelete('"+sId+"');\">删除</a> ';\r\n");
//        sb.append(t11+"return view+edit+dele;\r\n");
//        return sb.toString();
//    }
//
//    /**
//     * 获取字段
//     * @param columnInfo
//     * @param length
//     * @return
//     */
//    private static String getColumns(String columnInfo,int length){
//        int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
//        //{field:'@objectId',title:'@title',width:@width}
//        String fieldName = StringUtil.initialStrToLower(columnInfo.split("-")[1]);
//        String remark = columnInfo.split("-")[2];
//        StringBuffer sb = new StringBuffer("{field:'");
//        sb.append(fieldName);
//        sb.append("',title:'"+remark);
//        sb.append("',width:'"+screenWidth/length+"'},\r\n");
//        return sb.toString();
//    }
//
//    /**
//     * 公共过滤器
//     * @param readLine
//     * @param tableForm
//     * @return
//     */
//    private static String filterCommon(String readLine,TableForm tableForm){
//        String packageName = tableForm.getPackageName();
//        String objectName = tableForm.getObjectName();
//        String currentTime = FormatTool.getCurrentDate(FormatTool.Y_M_D_H_M);
//        readLine = readLine.replaceAll("@packageName;","package "+packageName);
//        readLine = readLine.replaceAll("@currentTime",currentTime);
//        readLine = readLine.replaceAll("@tableName",StringUtil.initialStrToLower(objectName));
//        readLine = readLine.replaceAll("@Object",objectName);
//        readLine = readLine.replaceAll("@object",StringUtil.initialStrToLower(objectName));
//        return readLine;
//    }
//
//    /**
//     * 创建单个文件
//     * @param destFileName 文件名
//     * @return 创建成功返回true，否则返回false
//     */
//    public static boolean createFile(String destFileName) {
//        File file = new File(destFileName);
//        if (file.exists()) {
//            log.info("创建单个文件" + destFileName + "失败，目标文件已存在！");
//            return false;
//        }
//        if (destFileName.endsWith(File.separator)) {
//            log.info("创建单个文件" + destFileName + "失败，目标不能是目录！");
//            return false;
//        }
//        if (!file.getParentFile().exists()) {
//            log.info("目标文件所在路径不存在，准备创建。。。");
//            if (!file.getParentFile().mkdirs()) {
//                log.info("创建目录文件所在的目录失败！");
//                return false;
//            }
//        }
//        // 创建目标文件
//        try {
//            if (file.createNewFile()) {
//                log.info("创建单个文件" + destFileName + "成功！");
//                return true;
//            } else {
//                log.info("创建单个文件" + destFileName + "失败！");
//                return false;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            log.info("创建单个文件" + destFileName + "失败！");
//            return false;
//        }
//    }
//
//    /**
//     * bean 的get方法加注解
//     * @param tableForm
//     * @param columnName
//     * @return
//     * @throws SQLException
//     */
//    private static String getAnnotations(TableForm tableForm,String columnName) throws SQLException{
//        StringBuffer sb = new StringBuffer();
//        DBFactory db = new DBFactory();
//        // 获取数据库连接
//        Connection conn = db.getDBConnectionInstance(tableForm.getModel()).getConnection();
//        boolean isPrimary = DBTool.isPrimaryKey(conn, tableForm.getSelecedTable(), columnName);
//        if(isPrimary){
//            sb.append("@Id\r\n");
//            sb.append("\t@GeneratedValue\r\n");
//        }else{
//            sb.append("@Column(name = \""+columnName+"\")\r\n");
//        }
//        return sb.toString();
//    }
//
//    /**
//     * 获取属性的声明
//     * @param columnInfo
//     * @param dbType
//     * @return
//     */
//    private static String getField(String columnInfo,String dbType){
//        String columnType = columnInfo.split("-")[0];
//        String fieldType = translateToJavaType(dbType,columnType);
//        String fieldName = StringUtil.initialStrToLower(columnInfo.split("-")[1]);
//        String remark = columnInfo.split("-")[2];
//        StringBuffer sb = new StringBuffer();
//        sb.append("/*\r\n");
//        sb.append("\t* "+remark+"\r\n");
//        sb.append("\t*/\r\n");
//        sb.append("\tprivate "+fieldType+" "+fieldName +";\r\n");
//        return sb.toString();
//    }
//
//    /**
//     * 获取get方法
//     * @param tableForm
//     * @param fieldName
//     * @return
//     * @throws SQLException
//     */
//    private static String getMethod(TableForm tableForm,String filedType,String fieldName) throws SQLException{
//        StringBuffer sb = new StringBuffer();
//        sb.append(getAnnotations(tableForm, fieldName));
//        sb.append("\tpublic " +filedType+" get"+StringUtil.initialStrToUpper(fieldName)+"(){\r\n");
//        sb.append("\t\t return " + "this."+fieldName+";\r\n");
//        sb.append("\t}\r\n \r\n");
//        return sb.toString();
//    }
//
//    /**
//     * 获取set方法
//     * @param tableForm
//     * @param fieldName
//     * @return
//     */
//    private static String setMethod(String tableName,String filedType,String fieldName){
//        StringBuffer sb = new StringBuffer();
//        sb.append("\tpublic void set"+StringUtil.initialStrToUpper(fieldName)+"("+filedType+" "+fieldName+"){\r\n");
//        sb.append("\t\t this."+fieldName+ "="+fieldName+";\r\n");
//        sb.append("\t}\r\n \r\n");
//        return sb.toString();
//    }
//
//    /**
//     * @param dbType
//     * @param columnType
//     * @return
//     */
//    private static String translateToJavaType(String dbType,String columnType){
//        ResourceBundle bundle = ResourceBundle.getBundle("datatype");
//        if(DBConnection.ORACLE_FLAG.equals(dbType)){
//            columnType = bundle.getString(DBConnection.ORACLE_FLAG+"."+columnType.toUpperCase());
//        }
//        if(DBConnection.MYSQL_FLAG.equals(dbType)){
//            columnType = bundle.getString(DBConnection.MYSQL_FLAG+"."+columnType.toUpperCase());
//        }
//        return columnType;
//    }
//
//    public static void main(String[] args) throws Exception {
//        String basePath = "com\\cxsoft\\".replaceAll("//", ".");
//        basePath = basePath.replaceAll("/", ".");
//        basePath = basePath.replaceAll("\\\\", ".");
//        System.out.println(basePath);
////      TableForm tableForm = new TableForm();
////      readAndWriteFile("../codeBuilder/template/ServiceTemplate.temp",tableForm);
//        //e:\com\cxsoft\service\IPersonService.java
////      writeFile("e:\\com\\cxsoft\\service\\IPersonService.java","122222222222");
//    }
//}
