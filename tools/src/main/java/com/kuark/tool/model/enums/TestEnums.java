package com.kuark.tool.model.enums;

/**
 * @author caoqingyuan
 * @detail
 * @date 2018/8/8 17:28
 */
public class TestEnums {
    public static void main(String[] args){
        BEnums.NS.setCode(AEnums.AS.getCode());
        BEnums.NS.setDesc(AEnums.AS.getDesc());
        Enums e=BEnums.NS;
        System.out.println("NS code1:"+e.getCode());
        System.out.println("NS desc1:"+e.getDesc());

        BEnums.NS.setCode(AEnums.BS.getCode());
        BEnums.NS.setDesc(AEnums.BS.getDesc());
        e=BEnums.NS;
        System.out.println("NS code2:"+e.getCode());
        System.out.println("NS desc2:"+e.getDesc());
    }
}
