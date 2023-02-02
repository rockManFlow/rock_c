package com.kuark.tool.advance.advance20200628;

import lombok.Data;

import java.util.function.Function;

/**
 * @author rock
 * @detail
 * @date 2020/7/7 15:33
 */
@Data
public class FunsV2 {
    public void fun(Function fu){
        System.out.println(fu.toString());
    }
}
