package com.kuark.tool.advance.advance20201111.lamda;

import lombok.Data;

/**
 * @author rock
 * @detail
 * @date 2021/1/25 15:16
 */
@Data
public class MyRequest {
    private String sumColumnName;
    public <T> MyRequest sum(MyFunction<T> func){
        String fieldName = FunctionHandler.getFieldName(func);
        System.out.println("fieldName:"+fieldName);
        this.sumColumnName=fieldName;
        return this;
    }
}
