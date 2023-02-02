package com.kuark.tool.base.test;

import org.apache.commons.collections.map.ListOrderedMap;
import org.junit.Test;

/**
 * Created by cc on 2016/5/24.
 * 测试ListOrderedMap的用法
 * 有序和以key=value为存储格式，是list和map特点的集合
 */
public class ListOrderedMapTest {
    @Test
    public void test(){
        ListOrderedMap list = new ListOrderedMap();
        list.put("sq","de");
        list.put("sq1","da");
        String st=(String)list.get(0);
        String nextKey = (String)list.nextKey(st);
        System.out.println("ss="+st);
        System.out.println("nextKey="+nextKey);
        int size = list.size();
        System.out.println("size="+size);
    }
}
