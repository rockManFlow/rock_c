package com.kuark.tool.advance.advance20201105util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * @author rock
 * @detail 可以通过此种方式来进行国际化
 * @date 2021/11/15 14:17
 */
@Slf4j
public class FileLoadUtil {
    private static Properties prop = null;
    public static Properties loadI18nProp(){
        if (prop != null) {
            return prop;
        }
        try {
            // build i18n prop
            String i18n = "";
            i18n = (i18n!=null && i18n.trim().length()>0)?("_"+i18n):i18n;
            String i18nFile = MessageFormat.format("i18n/message{0}.properties", i18n);

            // load prop--使用spring的ClassPathResource及工具
            Resource resource = new ClassPathResource(i18nFile);
            EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");
            prop = PropertiesLoaderUtils.loadProperties(encodedResource);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return prop;
    }
}
