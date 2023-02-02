package com.kuark.tool.advance.advance20201111.queue;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.PriorityQueue;

/**
 * @author rock
 * @detail PriorityQueue验证优先级队列，非阻塞队列
 * @date 2021/2/19 10:26
 */
public class PriorityQueueMain {
    public static void main(String[] args) {
        PriorityQueue priorityQueue=new PriorityQueue();
        PriVo v1=new PriorityQueueMain().new PriVo();
        v1.setAge(20);
        v1.setName("xxx");
        PriVo v2=new PriorityQueueMain().new PriVo();
        v2.setAge(10);
        v2.setName("yyy");
        priorityQueue.add(v1);
        priorityQueue.add(v2);
        while (!priorityQueue.isEmpty()){
            System.out.println(priorityQueue.poll());
        }
    }

    @Data
    class PriVo implements Comparable{
        private String name;
        private Integer age;

        @Override
        public int compareTo(@NotNull Object o) {
            return 0;
        }
    }
}
