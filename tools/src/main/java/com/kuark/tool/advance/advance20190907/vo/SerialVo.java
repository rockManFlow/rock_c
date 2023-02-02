package com.kuark.tool.advance.advance20190907.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author rock
 * @detail
 * @date 2019/9/7 15:12
 */
@Setter
@Getter
public class SerialVo implements Serializable{
    private String name;
    private int age;
    private transient String pwd;
}
