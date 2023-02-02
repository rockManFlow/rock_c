package com.kuark.tool.advance.advance20201105util;

/**
 * @author rock
 * @detail
 * @date 2020/10/13 14:21
 */
public class HtmlUtil {
    public static void main(String[] args) {
        String st="";
        String s = html2Text(st);
        System.out.println(s);
    }



    /**
     * 把HTML源码转成仅剩文本内容
     * @param htmlString
     * @return
     */
    public static String html2Text(String htmlString) {
        //删除脚本
        htmlString = htmlString.replaceAll("<script[^>]*?>.*?</script>", "")
                .replaceAll("<(.[^>]*)>", "")
                .replaceAll("([/r/n])[/s]+", "")
                .replaceAll("-->", "")
                .replaceAll("<!--.*", "")
                .replaceAll("&(quot|#34);", "/")
                .replaceAll("&(amp|#38);", "&")
                .replaceAll("&(lt|#60);", "<")
                .replaceAll("&(gt|#62);", ">")
                .replaceAll("&(nbsp|#160);", "   ")
                .replaceAll("&(iexcl|#161);", "/xa1")
                .replaceAll("&(cent|#162);", "/xa2")
                .replaceAll("&(pound|#163);", "/xa3")
                .replaceAll("&(copy|#169);", "/xa9")
                .replaceAll("&#(/d+);", "")
                .replaceAll("<", "")
                .replaceAll(">", "").replaceAll("/r/n", "");
        //返回去掉html标记的字符串
        return htmlString;
    }
}
