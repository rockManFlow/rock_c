package com.kuark.tool.advance.advance20200313;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caoqingyuan
 * @detail
 * @date 2020/3/13 17:56
 */
public class MainTest {
    public static void main(String[] args){
        List<String> list=new ArrayList<>();
        //内部也是循环遍历
//        list.contains("a");
        list.add("111");
        list.add("222");
        System.out.println(list);

        List<String> list2=new ArrayList<>();
        list2.add("444");
        list2.add("333");
        System.out.println(list2);

        list.addAll(list2);

        System.out.println(list);

        Map<Integer,String> resp=new HashMap<>();
        resp.put(1,"airtime");
        resp.put(2,"betting");
        System.out.println(resp);
    }
}
