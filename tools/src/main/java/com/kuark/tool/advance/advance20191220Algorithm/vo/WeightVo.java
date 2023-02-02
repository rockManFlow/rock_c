package com.kuark.tool.advance.advance20191220Algorithm.vo;

import lombok.Data;

/**
 * @author rock
 * @detail
 * @date 2020/1/3 14:44
 */
@Data
public class WeightVo<T> {
    //起始值
    private Integer startValue;
    //终止值
    private Integer endValue;
    //对象
    private T data;
}
