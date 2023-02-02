package com.kuark.tool.advance.advance20201111;

import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author rock
 * @detail
 * @date 2021/1/4 14:51
 */
@ToString
public class Apple implements Fruit {
    static {
        System.out.println("Apple init");
    }
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
