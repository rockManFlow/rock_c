package com.kuark.tool.advance.advance20190724Thread.queue;

import lombok.Data;

/**
 * @author rock
 * @detail
 * @date 2022/2/15 10:16
 */
@Data
public class PriorityMessage{
    private int age;
    private String name;
    public PriorityMessage(String name,int age){
        this.name=name;
        this.age=age;
    }
//    @Override
//    public int compareTo(@NotNull PriorityMessage other) {
//        if(this.getAge()>other.getAge()){
//            return 1;
//        }else if(this.getAge()<other.getAge()){
//            return -1;
//        }else {
//            return 0;
//        }
//    }
}
