package com.kuark.tool.advance.advance20201105util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 类CopyUtils.java的实现描述：TODO 类实现描述
 * 
 * @author wanglinan 2019年4月18日 下午10:14:10
 */
public class CopyUtils {

    public static ByteArrayOutputStream cloneInputStream(InputStream input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = input.read(buffer)) > -1) {
            baos.write(buffer, 0, len);
        }
        baos.flush();
        input.close();
        return baos;
    }
     /**
      * 复制  InputStream
      * @param input
      * @return
      * @throws IOException
      */
    public static ByteArrayInputStream copyInputStream(InputStream input) throws IOException {
        return new ByteArrayInputStream(cloneInputStream(input).toByteArray());
    }

}
