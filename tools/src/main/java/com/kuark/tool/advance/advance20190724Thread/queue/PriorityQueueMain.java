package com.kuark.tool.advance.advance20190724Thread.queue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author rock
 * @detail PriorityQueue 优先级队列
 * @date 2022/2/15 10:16
 */
public class PriorityQueueMain {
    public static void main(String[] args) {
        Comparator comparator=new Comparator<PriorityMessage>(){
        public int compare(PriorityMessage o1, PriorityMessage other) {
            if(o1.getAge()>other.getAge()){
                return 1;
            }else if(o1.getAge()<other.getAge()){
                return -1;
            }else {
                return 0;
            }
        }};
        PriorityQueue<PriorityMessage> queue=new PriorityQueue(5,comparator);
//        for(int i=0;i<5;i++){
//            PriorityMessage message=new PriorityMessage("xiao"+i,i);
//            queue.add(message);
//        }
        for(int i=4;i>=0;i--){
            PriorityMessage message=new PriorityMessage("xiao"+i,i);
            queue.add(message);
        }

        for(int i=0;i<5;i++){
            System.out.println(queue.poll());
        }

        // 遍历方式并不是按照顺序遍历的，只有上面按照个数来取才是顺序的
//        Iterator<PriorityMessage> iterator = queue.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
    }


}
