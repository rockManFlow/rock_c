package com.kuark.tool.model.enums;

/**
 * @author caoqingyuan
 * @detail
 * @date 2018/8/8 17:24
 */
public enum AEnums implements Enums{
    AS("1","SSS"),
    BS("2","AAA"),
    ;

    private String code;
    private String desc;

    AEnums(String code,String desc){
        this.code=code;
        this.desc=desc;
    }

    public String getCode(){
        return this.code;
    }

    public String getDesc(){
        return this.desc;
    }
}
