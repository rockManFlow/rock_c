package com.kuark.tool.advance.advance20190905;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author rock
 * @detail
 * @date 2019/9/6 15:56
 */
public class CollectionTest {
    public static void main(String[] args){

    }

    /**
     * 数据集合简介
     */
    public static void coll(){
        /**
         * 线程安全的set集合
         * 基于CopyOnWriteArrayList实现的，该类的add方法使用ReentrantLock锁实现的线程安全
         * （新增数据会新建一个数组+1并把该新数据放进去）
         */
        CopyOnWriteArraySet copyOnWriteArraySet=new CopyOnWriteArraySet();
//        copyOnWriteArraySet.add();
//        Set接口
        HashSet hashSet=new HashSet(2);
        /**
         * 无序、内部使用HashMap来进行存储，key=对象的地址  value=公共创建的一个Object对象
         * 不可重复（已equal()和hashcode()来比较是否重复
         * 允许null值，但只允许一个
         */
        LinkedHashSet linkedHashSet=new LinkedHashSet();
        /**
         * 基于LinkedHashMap实现的，有序（插入顺序），不可重复，内容与HashSet一致
         */
        TreeSet treeSet=new TreeSet();
        /**
         * 基于TreeMap实现，去重，排序（自定义类型需要指定排序方法）
         */
//        List接口
        LinkedList linkedList=new LinkedList();
        /**
         * 双链表，插入删除性能好，随机访问性能差，非线程安全
         */
        ArrayList arrayList=new ArrayList();
        /**
         * 对象数组来存的，下标为序，随机查询好，插入删除差，线程不安全
         */
        Vector vector=new Vector();
        /**
         * 已对象数组来存储，线程安全（已synchronized来实现线程安全）
         */
        Stack stack=new Stack();
        /**
         * 继承Vector 线程安全 先进后出堆栈对象
         */
//        Map
        HashMap hashMap=new HashMap(2);
        hashMap.put("11",1);
        hashMap.get("11");
        /**
         * 键值对，以hash表来存储的，非线程安全，允许一个key为null，值不限制是否为空
         * HashMap是由数组+链表组成
         * Collections的synchronizedMap方法使HashMap具有同步的能力，
         * 或者使用ConcurrentHashMap。
         */
        LinkedHashMap linkedHashMap=new LinkedHashMap();
        linkedHashMap.put("","");
        /**
         * 是hashMap的子类，保存了记录的插入顺序，遍历先获取的肯定是先插入的，实现和hashMap一致
         * 双向链表来存储位置，node存key-value
         */
        Hashtable hashtable=new Hashtable();
        /**
         * 线程安全，使用synchronized实现  key-value形式，
         * 它不允许记录的键或者值为空
         */
        Properties properties=new Properties();
        /**
         * 继承hashtable
         */
        TreeMap treeMap=new TreeMap();
        /**
         *   1.无序，不允许重复（无序指元素顺序与添加顺序不一致）
             2.TreeMap集合默认会对键进行排序，所以键必须实现自然排序和定制排序中的一种
             3..底层使用的数据结构是二叉树
            TreeMap本身提供了一个tailMap(K fromKey)方法，支持从红黑树中查找比fromKey大的值的集合，但并不需要遍历整个数据结构。
         */

        //使用工具类可以吧非线程安全的集合变成线程安全的集合
        Collections.synchronizedMap(hashMap);

        ConcurrentHashMap concurrentHashMap=new ConcurrentHashMap();
        concurrentHashMap.put("11",1);
        concurrentHashMap.get("");
        concurrentHashMap.size();
        /**
         * 线程安全，使用了锁分离技术，可以实现并发写操作。因为有多个Segment（也是hash table）段来
         * 存储数据，不同的Segment使用不同的锁，只要写操作是发生在不同的Segment上就可以并发执行
         *完全允许多个读操作并发进行，读操作并不需要加锁
         * 有些方法需要跨段，比如size()和containsValue()，
         * 它们可能需要锁定整个表而而不仅仅是某个段，这需要按顺序锁定所有段，操作完毕后，
         * 又按顺序释放所有段的锁。这里“按顺序”是很重要的，否则极有可能出现死锁，
         * 在ConcurrentHashMap内部，段数组是final的，并且其成员变量实际上也是final的，
         * 但是，仅仅是将数组声明为final的并不保证数组成员也是final的，这需要实现上的保证。
         * 这可以确保不会出现死锁，因为获得锁的顺序是固定的。不变性是多线程编程占有很重要的地位
         * 1.8采用了数据+链表+红黑树的结构 使用的是CAS和synchronized机制
         */

        ReentrantLock reentrantLock=new ReentrantLock();
    }
}
