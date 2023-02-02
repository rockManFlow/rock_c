package com.kuark.tool.model.enums;

/**
 * @author caoqingyuan
 * @detail
 * @date 2018/8/8 17:24
 */
public enum BEnums implements Enums{
    AS("1","BBB"),
    BS("2","QQQ"),
    NS("",""),
    ;

    private String code;
    private String desc;

    BEnums(String code,String desc){
        this.code=code;
        this.desc=desc;
    }

    public String getCode(){
        return this.code;
    }

    public void setCode(String code){
        this.code=code;
    }

    public String getDesc(){
        return this.desc;
    }

    public void setDesc(String desc){
        this.desc=desc;
    }
}
