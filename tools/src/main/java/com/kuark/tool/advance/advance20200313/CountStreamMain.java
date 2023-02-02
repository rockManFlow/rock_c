package com.kuark.tool.advance.advance20200313;

import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author rock
 * @detail todo jdk8集合中元素计数和方式
 * @date 2020/4/29 15:08
 */
public class CountStreamMain {
    public static void main(String[] args) {
        distinct();
    }

    /**
     * 拆分集合指定个数
     */
    private static void count(){
        String[] awards = "1,2,3,3,4,4,5".split(",");
        Integer collect = Arrays.stream(awards).collect(Collectors.summingInt(x -> (Integer.parseInt(x))));
        System.out.println(collect);

        Map<String,String> finishReferIdMap = new HashMap();
        finishReferIdMap.put("111","name");
        finishReferIdMap.put("222","age");
        List<String> records=Arrays.asList("888","111","333","222","555");
        List<String> result = records.stream().filter(taskListDto -> {
            if (finishReferIdMap.get(taskListDto) != null) {
                return false;
            }
            return true;
        }).collect(Collectors.toList()).subList(0,2);//TODO 挑选出前2条，注意list集合范围，超出汇报异常

        System.out.println(result);
    }

    //根据指定字段或多个字段去重
    private static void distinct(){
        List<FlightTicketInfo> infoList = new ArrayList<>();
        infoList.add(new FlightTicketInfo("11111", "xiaoming", "22",20L));
        infoList.add(new FlightTicketInfo("22222", "xiaoming", "22",22L));
        infoList.add(new FlightTicketInfo("33333", "xiaoming", "23",21L));
        infoList.add(new FlightTicketInfo("11111", "xiaoming", "22",20L));
        infoList.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(f -> f.getUserName()+f.getAge()))), ArrayList::new))
                .forEach(System.out::println);

        ArrayList<FlightTicketInfo> collect = infoList.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(f -> f.getUserName() + f.getAge()))), ArrayList::new));
    }

    @Data
    public static class FlightTicketInfo {
        private Long id;
        private String orderNumber;

        private String userName;

        private String age;
        public FlightTicketInfo(String orderNumber, String userName, String age,Long id) {
            this.orderNumber = orderNumber;
            this.userName = userName;
            this.age = age;
            this.id=id;
        }
    }

}
