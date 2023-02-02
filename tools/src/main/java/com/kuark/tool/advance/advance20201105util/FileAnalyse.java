package com.kuark.tool.advance.advance20201105util;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.junit.Test;

import java.io.*;

/**
 * Created by caoqingyuan on 2016/9/18.
 * 文件内容的解析
 */
public class FileAnalyse {
    private static String urlFile="E:\\test/file/11.txt";
    public static void main(String[] args) throws IOException {
        File file=new File(urlFile);
        if(!file.exists()){
            boolean file1 = file.isDirectory();
            System.out.println(file1);
//            boolean mkdirs = file.mkdirs();
//            System.out.println(mkdirs);
        }
        FileOutputStream out=new FileOutputStream(file);
        out.write("ccccc".getBytes());
        out.flush();
        out.close();

    }
    public String readFile(File file) throws IOException {
        FileInputStream inputStream=new FileInputStream(file);
        StringBuilder builder=new StringBuilder();
        byte[] bytes=new byte[1024];
        while (inputStream.read(bytes)!=-1){
            builder.append(new String(bytes,"UTF-8"));
        }
        return builder.toString();
    }
    @Test
    public void test(){
        String str = "<script type='text/javascript'>" +
                "        if (window.location.toString().indexOf('shtml?') > 0) {" +
                "            self.location = window.location.toString().replace(window.location.search.toString(), '');" +
                "        }" +
                "        function doccheckart(json) {" +
                "            if (json[0].result == '1') {" +
                "                self.location = 'http://www.360doc.com/noarticle.aspx';" +
                "            }" +
                "        }" +
                "    </script><table><tr><td>sdasasdsdd</td></tr></table><br><p>呵呵</p><img id='img1' src='http://www.baidu.com/img/baidu_logo.gif' width='100' height='50' alt=''>图片<br><img src='http://www.baidu.com/img/baidu_logo.gif' width='100' height='50' alt=''> 说是道 ";
        String html = "<[^>]*>";    //去除html所有的标签
        //.*?  表示0到1个以上任意字符?表示轮替
        String script="^(<script)[^>]*>.*(</script>)$"; //去除所有脚本，中间部分也删除
        String img ="<img[^>]*>";   //去除图片的正则
        //()表示一个组合
        String br ="<(?!br).*?>";   //去除所有标签，只剩br
        // string regexstr = @"<table[^>]*?>.*?</table>";   //去除table里面的所有内容
//        string regexstr = @"<(?!img|br|p|/p).*?>";   //去除所有标签，只剩img,br,p
//        str = Regex.Replace(str, regexstr, string.Empty, RegexOptions.IgnoreCase);
        String out=str.replaceAll(script,"");
        System.out.println(out);
    }
}
