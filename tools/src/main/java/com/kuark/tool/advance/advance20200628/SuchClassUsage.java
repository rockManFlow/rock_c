package com.kuark.tool.advance.advance20200628;

import com.google.common.base.Joiner;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import lombok.Data;

import java.util.Collection;

/**
 * @author rock
 * @detail 一些类的用法
 * @date 2020/6/28 14:46
 */
public class SuchClassUsage {
    public static void main(String[] args) {
        multiMap();
    }

    /**
     * 获取os名称
     */
    private static void osName(){
        String osName = System.getProperty("os.name");
        System.out.println("osName:"+osName);
    }

    /**
     * google Joiner用法
     */
    private static void googleJoin(){
        Joiner STRING_JOINER = Joiner.on("+")
                .skipNulls();
        String appname = STRING_JOINER.join("appname", "2222");
        System.out.println(appname);//appname+2222
    }

    /**
     * Multimap用法
     * multimap的特点为key是可以重复的，一个key可以获取到多个value值
     */
    private static void multiMap(){
        Multimap<String, MultiVo> multimap = Multimaps.synchronizedSetMultimap(HashMultimap.create());
        SuchClassUsage.MultiVo v1=new SuchClassUsage().new MultiVo();
        v1.setAge(20);v1.setName("xiaohong");
        SuchClassUsage.MultiVo v2=new SuchClassUsage().new MultiVo();
        v2.setAge(10);v2.setName("xiaohei");
        multimap.put("AA",v1);
        multimap.put("AA",v2);
        Collection<MultiVo> result = multimap.get("AA");
        result.stream().forEach(multiVo -> {
            System.out.println(multiVo);
        });
    }

    @Data
    public class MultiVo{
        private String name;
        private Integer age;
    }
}
