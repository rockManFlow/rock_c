package com.kuark.tool.advance.advance20201111;

import lombok.ToString;

/**
 * @author rock
 * @detail
 * @date 2021/1/4 14:50
 */
@ToString
public class Store {
    private String name;
    private Fruit fruit;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Fruit getFruit() {
        return fruit;
    }
    public void setFruit(Fruit fruit) {
        this.fruit = fruit;
    }
}
