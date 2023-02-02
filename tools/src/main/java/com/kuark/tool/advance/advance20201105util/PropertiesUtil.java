package com.kuark.tool.advance.advance20201105util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by cc on 2016/4/14.
 */
public class PropertiesUtil {
    private static Logger logger=Logger.getLogger(PropertiesUtil.class);
    public static Properties getFiles(){
        Properties props = new Properties();
        InputStream in=null;
        try {
            in = PropertiesUtil.class.getResourceAsStream("/jdbc.properties");
            if(in==null){
                throw new IOException();
            }
            props.load(in);
        } catch (IOException e) {
            logger.error("读取jdbc配置文件异常",e);
        }finally {
            if(in!=null){
                try{
                    in.close();
                }catch(IOException e){
                    logger.error("读取配置文件流关闭异常",e);
                }
            }
        }
        return props;
    }
    public void test(){
        Properties props= PropertiesUtil.getFiles();
        String driver=props.getProperty("dayuv2.driver");
        String username=props.getProperty("dayuv2.username");
        String password=props.getProperty("dayuv2.password");
        System.out.println("db driver:"+driver);
        System.out.println("db username:"+username);
        String virtualUrl=props.getProperty("virtual.url");
        System.out.println("virtualUrl:"+virtualUrl);
        String archive_config_dbname=props.getProperty("archive_config_dbname");
        System.out.println("archive_config_dbname:"+archive_config_dbname);
    }
}
