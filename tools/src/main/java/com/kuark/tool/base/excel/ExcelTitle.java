package com.kuark.tool.base.excel;

import java.lang.annotation.*;

/**
 * 类ExcelTitle.java的实现描述：
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelTitle {
    String value();
}
