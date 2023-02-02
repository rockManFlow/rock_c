package com.kuark.tool.advance.advance20201111;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.math.BigDecimal;

/**
 * @author rock
 * @detail
 * @date 2020/12/31 18:36
 */
public class FastJsonMain {
    public static void main(String[] args) {
        String userJson = "{\"@type\":\"com.kuark.tool.advance.advance20201111.User\",\"userName\":\"testUserName\"}";

        //parseObject≤‚ ‘
        Object object1 = JSON.parseObject(userJson);
        System.out.println("=============");
        //parse
        Object object2 = JSON.parse(userJson);

        Store store = new Store();
        store.setName("Hollis");
        Apple apple = new Apple();
        apple.setPrice(new BigDecimal(0.5));
        store.setFruit(apple);//
        String jsonString = JSON.toJSONString(store, SerializerFeature.WriteClassName);
        System.out.println("toJSONString : " + jsonString);

        Store newStore = JSON.parseObject(jsonString, Store.class);
        System.out.println("parseObject : " + newStore);
        Apple newApple = (Apple)newStore.getFruit();
        System.out.println("getFruit : " + newApple);
    }

    public static void test(){
        String jsonString="{\"@type\":\"com.kuark.tool.advance.advance20201111.Store\",\"fruit\":{\"@type\":\"com.kuark.tool.advance.advance20201111.Apple\",\"price\":0.5},\"name\":\"Hollis\"}";
        Store newStore = JSON.parseObject(jsonString, Store.class);
        System.out.println("parseObject : " + newStore);
        Apple newApple = (Apple)newStore.getFruit();
        System.out.println("getFruit : " + newApple);
    }
}
