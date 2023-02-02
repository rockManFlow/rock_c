package com.kuark.tool.advance.advance20191115;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author rock
 * @detail java.util.stream.Collectors用法
 * @date 2019/11/27 11:32
 */
public class CollectionsMain {
    public static void main(String[] args){
        chooseAndSort();
    }

    public static void chooseAndSort(){
        List<StreamVo> streamVos = buildList();
        final Integer failCount=2;
        List<StreamVo> chooseAndSortResultList = streamVos.stream().filter(dto -> {
            return Long.valueOf(dto.getNum()) > failCount;
        }).sorted((dto, dto2) -> {
            if (Long.valueOf(dto.getNum()) >= Long.valueOf(dto2.getNum())) {
                return -1;
            }
            return 1;
        }).collect(Collectors.toList());
        System.out.println(chooseAndSortResultList);
    }

    /**
     * 根据指定条件挑选对象集合
     */
    public static void optionConditionOb(){
        List<StreamVo> voList = buildList();
        //分组--根据什么来分
        //todo 根据一个对象判断是否为相同的方法，是相同的对象放到一个set中
        //todo 这个很重要
        Map<String, Set<Object>> obMap= voList.stream().collect(Collectors.groupingBy(StreamVo::getKey, Collectors.mapping(Function.identity(), Collectors.toSet())));

        obMap.keySet().stream().forEach(uniKey->{
            System.out.println("Key:"+uniKey+"|Value:"+obMap.get(uniKey));
            Object[] objectArray = obMap.get(uniKey).toArray();
            if(objectArray.length>1){
                Integer sum =Integer.valueOf(((StreamVo)objectArray[0]).getNum());
                for(int i=1;i<objectArray.length;i++){
                    sum =sum+Integer.valueOf(((StreamVo)objectArray[i]).getNum());
                    voList.remove(objectArray[i]);
                }
                ((StreamVo)objectArray[0]).setNum(String.valueOf(sum));
            }
        });
        System.out.println(voList);
    }

    /**
     * 集合中统计指定字符出现的次数--基本数据类型
     */
    public static void streamCount(){
        //定义一个100元素的集合，包含A-Z
        List<String> list = new LinkedList<>();
        for (int i =0;i<100;i++){
            list.add(String.valueOf((char)('A'+Math.random()*('Z'-'A'+1))));
        }
        System.out.println(list);
        //统计集合重复元素出现次数，并且去重返回hashmap
        Map<String, Long> map = list.stream().
                collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(map);
    }
    
    private static List<StreamVo> buildList(){
        StreamVo v1=new StreamVo();
        v1.setName("111");
        v1.setLocal("222");
        v1.setInfo("333");
        v1.setDesc("KX111");
        v1.setNum("5");
        StreamVo v2=new StreamVo();
        v2.setName("111");
        v2.setLocal("222");
        v2.setInfo("333");
        v2.setDesc("KX222");
        v2.setNum("3");
        StreamVo v3=new StreamVo();
        v3.setName("111");
        v3.setLocal("222");
        v3.setInfo("666");
        v3.setDesc("KX333");
        v3.setNum("2");
        StreamVo v4=new StreamVo();
        v4.setName("111");
        v4.setLocal("222");
        v4.setInfo("333");
        v4.setDesc("KX444");
        v4.setNum("6");
        StreamVo v5=new StreamVo();
        v5.setName("111");
        v5.setLocal("222");
        v5.setInfo("666");
        v5.setDesc("KX555");
        v5.setNum("7");
        List<StreamVo> list=new ArrayList<>();
        list.add(v1);
        list.add(v2);
        list.add(v3);
        list.add(v4);
        list.add(v5);
        return list;
    }
}
