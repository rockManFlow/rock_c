package com.kuark.tool.base.test;

import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by cc on 2016/5/30.
 */
public class QueueTest {
    //PriorityQueue 队列中数据会按照自然顺序或指定的顺序排列
    private static Queue queue=new PriorityQueue();
    @Test
    public void createQueue(){
        String data1="hello";
        String data2="world";
        String data3="ni";
        String data4="hao";
        String data5="23";
        addDataToQueue(data1);
        addDataToQueue(data1);
        addDataToQueue(data1);
        addDataToQueue(data2);
        addDataToQueue(data3);
        addDataToQueue(data4);
        addDataToQueue(data5);
        while (!queue.isEmpty()){
            System.out.println("ddd="+getDataFromQueue());
        }
    }
    //进队
    public static void addDataToQueue(String data){
        queue.add(data);
    }
    //出队
    public static String getDataFromQueue(){
        return (String)queue.poll();
    }
}
