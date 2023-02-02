package com.kuark.tool.model.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by caoqingyuan on 2018/1/11.
 */
public class DataUtil {
    public static String getCournt(){
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
}
