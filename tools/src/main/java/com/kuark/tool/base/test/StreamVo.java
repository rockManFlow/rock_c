package com.kuark.tool.base.test;

import lombok.Data;

/**
 * @author rock
 * @detail
 * @date 2019/11/26 17:11
 */
@Data
public class StreamVo {
    private String name;
    private String local;
    private String desc;
    private String info;

    private String num;
    public String getA(){
        String key="KX";
        if(this.desc.startsWith(key)){
            return name+local+info+key;
        }
        return name+local+info+desc;
    }

//    public String toString(){
//        return name+local+info+desc;
//    }
}
