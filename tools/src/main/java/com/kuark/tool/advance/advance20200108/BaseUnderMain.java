package com.kuark.tool.advance.advance20200108;

/**
 * @author rock
 * @detail
 * @date 2020/1/8 10:14
 */
public class BaseUnderMain {
    public static void main(String[] args){
        String msgInfo=" ";

        //去掉各种空值
        String msg=msgInfo!=null?msgInfo.replaceAll("\u0020","").replaceAll("\u3000","").replaceAll("\u00A0",""):null;
        System.out.println("Msg="+msg);

        //比较也是比较的char字节
        boolean b=msgInfo.equals("\u0020");
        System.out.println("equals="+b);

        //获取字符的Unicode码值
        String uniCodeTemp = "\\u"+Integer.toHexString((int)msgInfo.charAt(0));
        System.out.println("uniCodeTemp="+uniCodeTemp);
    }
}
