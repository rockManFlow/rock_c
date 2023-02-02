package com.kuark.tool.advance.advance20190905;

import java.util.concurrent.*;

/**
 * @author rock
 * @detail
 * @date 2019/9/7 15:02
 */
public class QueueTest {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue arrayBlockingQueue=new ArrayBlockingQueue(100);
        // 取不是阻塞的，没有返回null
        arrayBlockingQueue.peek();
        arrayBlockingQueue.poll();
        // 放数据阻塞
        arrayBlockingQueue.put("");
        arrayBlockingQueue.wait();

        /**
         * 指定队列大小，不可变。底层是以对象数组来存储，先进先出，
         * 线程安全的，因为加了ReententLock锁，读和写使用了同一个锁
         * 使用Condition来控制满和空
         */
        LinkedBlockingQueue linkedBlockingQueue=new LinkedBlockingQueue();
        linkedBlockingQueue.peek();
        //放数据是阻塞的
        linkedBlockingQueue.put("xxxx");
        /**
         * 底层以链表存储，线程安全的，读和写使用了两个不同的锁
         * 采用了锁分离的设计，避免了读/写操作冲突
         */

        LinkedBlockingDeque linkedBlockingDeque=new LinkedBlockingDeque();
        linkedBlockingDeque.add(null);
        linkedBlockingDeque.offer(null);
        /**
         * Deque(双端的意思):这种的是实现的双向(双头)队列(实际还是一个队列，但该队列的头尾都可以存取数据)，可以在队列两端存取数据
         * 不同点在于栈是“后入先出”的结构，每次操作的是栈顶，而队列是“先进先出”的结构，
         * 每次操作的是队列头。
         * 线程安全的 lock实现
         */

        ConcurrentLinkedQueue concurrentLinkedQueue=new ConcurrentLinkedQueue();
        /**
         * 线程安全链表队列,不允许存null，适用于高并发读写操作，理论上有最高的吞吐量
         * 采用CAS机制实现无锁访问
         * 在并发的场景下，如果并发强度较小，性能要求不苛刻，且锁可控的场景下，可使用Collections.synchronizedList，既保证了数据一致又保证了线程安全，性能够用；
         在大部分高并发场景下，建议使用 LinkedBlockingQueue ，性能与 ConcurrentLinkedQueue 接近，且能保证数据一致性；
         ConcurrentLinkedQueue 适用于超高并发的场景，但是需要针对数据不一致采取一些措施。
         无锁算法，基于cas实现
         */

        ConcurrentLinkedDeque concurrentLinkedDeque=new ConcurrentLinkedDeque();
        /**
         * Deque是一种双端队列，也就是说可以在任意一端进行“入队”，也可以在任意一端进行“出队”
         * 继承自Queue接口 可以并发的当做栈使用，  通过无锁的cas自旋来保证线程安全
         * 实现的是一种线性安全的栈结构与Stack类似
         * size方法需要遍历链表，所以在并发情况下，其结果不一定是准确的，只能供参考。
         */
        SynchronousQueue synchronousQueue=new SynchronousQueue();
        /**
         * 同步队列
         * 它一种阻塞队列，其中每个 put 必须等待一个 take，反之亦然。
         * 同步队列没有任何内部容量，甚至连一个队列的容量都没有。
         * 它是线程安全的，是阻塞的。
         * 不允许使用 null 元素。
         */



        DelayQueue delayQueue=new DelayQueue();
        /**
         * 延迟队列
         * DelayQueue是一个无界的BlockingQueue，用于放置实现了Delayed接口的对象，
         * 其中的对象只能在其到期时才能从队列中取走。这种队列是有序的。谁先到期就先获取谁，
         * 如果都到期了，会按照队列顺序来获得
         * 即队头对象的延迟到期时间最长。注意：不能将null元素放置到这种队列中。
         * 队列中只能放入Delay接口的对象，在这个对象中需要实现
         */
    }
}
