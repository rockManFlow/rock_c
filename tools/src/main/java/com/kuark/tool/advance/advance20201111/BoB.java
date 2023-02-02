package com.kuark.tool.advance.advance20201111;

import com.google.common.collect.Maps;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

/**
 * @author rock
 * @detail
 * @date 2021/8/3 17:29
 */
public class BoB{
    public BoB(){
        //3
        System.out.println("init BoB");
    }
    {
        //2
        System.out.println("base BoB context");
    }
    static {
        //1
        System.out.println("static BoB");
    }

    public static void main(String[] args) {
        new BoA();
        new BoB();

        BoC c = new BoC();
        System.out.println(c);

        Map<TestEnum,String> map= Maps.newHashMap();
        map.put(TestEnum.OPEN_AUTO_INVEST,"111");
        map.put(TestEnum.ADD_HOLDER_CARD_USER,"222");
        Optional.ofNullable(map.keySet()).orElseGet(HashSet::new).stream()
                .forEach(e->{
                    System.out.println(map.get(e));
                    System.out.println(e);
                });
        System.out.println(TestEnum.ADD_HOLDER_CARD_USER==TestEnum.ADD_HOLDER_CARD_USER);
    }
}
