package com.kuark.tool.advance.advance20210810;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author rock
 * @detail safetoken
 * @date 2021/12/10 16:17
 */
public class TestMain {
    private static final long multiplier = 0x5DEECE66DL;
    private static final long addend = 0xBL;
    private static final long mask = (1L << 48) - 1;
    public static void main(String[] args) throws InterruptedException, NoSuchAlgorithmException {
//        System.out.println(new BigDecimal("32.24").multiply(new BigDecimal(100)).toString());
//        date();

//        String cardNo="1234567890";
//        System.out.println(cardNo.substring(cardNo.length()-4));
//        String str="12345";
//        Pattern pattern = Pattern.compile("\\d{6}");
//        Matcher isNum = pattern.matcher(str);
//        System.out.println(isNum.matches());
//
//        String substring = str.substring(0, 6);
//        System.out.println("q:"+substring);
//        atomic();
        // 这个是JVM启动时的参数列表
//        Properties properties = System.getProperties();
//        String property = properties.getProperty("user.dir");
//        System.out.println(property);
//        String property2 = properties.getProperty("sun.net.client.defaultConnectTimeout");
//        System.out.println(property2);
//        String virtualClientId = "IKIA9614B82064D632E9B6418DF358A6A4AEA84D7218";
//        String virtualClientSecret = "XCTiBtLy1G9chAnyg0z3BcaFK4cVpwDg/GTw2EmjTZ8=";

        //new
//        String virtualClientId = "IKIA3A7E63FF6663AC129DE8684B99BE42DD35748FCD";
//        String virtualClientSecret = "vCXaJrT6Jq5HGxHFYNns5ORIROtGtWeW+GuKusgDtoI";

        //old
//        String virtualClientId = "IKIA3A7E63FF6663AC129DE8684B99BE42DD35748FCD";
//        String virtualClientSecret = "FhRnIytjbYMxJnGSRFKflfurM+3vHBnhAvXLh5v1Ftw=";
//        String st = virtualClientId + ":" + virtualClientSecret;
//        String base = Base64.getEncoder().encodeToString(st.getBytes());
//        System.out.println(base);
//        boolean equals = base.equals("SUtJQTk2MTRCODIwNjRENjMyRTlCNjQxOERGMzU4QTZBNEFFQTg0RDcyMTg6WENUaUJ0THkxRzljaEFueWcwejNCY2FGSzRjVnB3RGcvR1R3MkVtalRaOD0=");
//        System.out.println(equals);

//        String ss=new String(Base64.getDecoder().decode("SUtJQTk3RjRDRDk3NzFGQjE5RjEzOUNDMTk3NzQ4QzMyNjI2RjBBMEQ1QzM=".getBytes()));
//        System.out.println(ss);
//
//        System.out.println("vCXaJrT6Jq5HGxHFYNns5ORIROtGtWeW+GuKusgDtoI".equals("vCXaJrT6Jq5HGxHFYNns5ORIROtGtWeW+GuKusgDtoI"));


//        String s = "SUtJQTNBN0U2M0ZGNjY2M0FDMTI5REU4Njg0Qjk5QkU0MkREMzU3NDhGQ0Q6dkNYYUpyVDZKcTVIR3hIRllObnM1T1JJUk90R3RXZVcrR3VLdXNnRHRvST0=";
//        String s1 = new String(Base64.getDecoder().decode(s));
//        System.out.println("dd:"+s1);
//
//        bitSet();
//        atomic();
//        System.out.println(String.format("%sDDD%s",12,34));
//
//        InstanceofTwo t=new InstanceofTwo();
//        if(t instanceof InstanceofTwo){
//            System.out.println("yes");
//        }
//
//        double c = 0.8;
//        double d = 0.7;
//        double e = 0.6;
//        System.out.println(c-d);
//        System.out.println(d-e);
//        System.out.println(ChronoUnit.DAYS.between(LocalDate.of(2022,11,10),LocalDate.of(2022,10,10)));

//        System.out.println(Pattern.compile("\\s+").matcher("12 34").find());
//        System.out.println(0==0);
//        AtomicInteger count=new AtomicInteger(0);
//        add(count);
//        System.out.println("main count:"+count.get());

        Random random=new Random(100);
        int i=0;
        while (i<5){
            System.out.println(random.nextInt());
            i++;
        }


        long oldseed=100, nextseed;
        nextseed = (oldseed * multiplier + addend) & mask;
        System.out.println("nextseed="+nextseed);
        int result=(int)(nextseed >>> (48 - 32));
        System.out.println("result="+result);
        /**
         * nextseed=2521490391711
         * result=38474890
         */

        SecureRandom secureRandom=new SecureRandom();
        secureRandom.nextInt();

        SecureRandom secureRandom1=SecureRandom.getInstance("SHA1PRNG");
        secureRandom1.nextInt();
    }

    private static void add(AtomicInteger count){
        System.out.println("before:"+count.get());
        count.incrementAndGet();
        System.out.println("end:"+count.get());
    }

    private static void date(){
        String date="2022-07-18T16:00:20.000Z";//
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime localDateTime = LocalDateTime.parse(date,formatter);
        System.out.println(localDateTime);

        DateTimeFormatter formatter2=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = localDateTime.format(formatter2);
        System.out.println(format);
    }

    private static void atomic() throws InterruptedException {
        // AtomicReference类来保证引用对象之间的原子性，可以把多个变?放在一个对象?
        //来进?CAS操作
        BeanTwo beanTwo=new BeanTwo();
        beanTwo.setAge(10L);
        beanTwo.setName("xiaohong");

        BeanTwo beanTwo2=new BeanTwo();
        beanTwo2.setAge(20L);
        beanTwo2.setName("xiaogao");
        CountDownLatch latch=new CountDownLatch(10);

        //初始当前值设置,相当于设置新对象的引用
        AtomicReference<BeanTwo> atomicReference=new AtomicReference<BeanTwo>(beanTwo);
        for (int i = 0; i < 10; i++)
        {
            new Thread() {
                public void run() {
                    try
                    {
                        //为了 使得控制台打印的 更改student1的线程 能显示出不一样 每个线程随机停顿 多执行几次能看出效果
                        Thread.sleep(Math.abs((int)Math.random()*100));
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    //预期值 student1和 当前值（上面的atomicStudent.set(student1);）相等时候 赋予student2新的值
                    //cas改变该新对象的引用为beanTwo2的引用地址
                    if (atomicReference.compareAndSet(beanTwo,beanTwo2))
                    {
                        System.out.println(Thread.currentThread().getId() + "Change value");
                        atomicReference.get().setName(atomicReference.get().getName()+"11");
                        System.out.println(atomicReference.get().getName()+":"+atomicReference.get().getAge());
                    }else {
                        System.out.println(Thread.currentThread().getId() + "Failed");
                    }
                    latch.countDown();
                };
            }.start();
        }

        latch.await();
        System.out.println(beanTwo2.getAge()+":"+beanTwo2.getName());

    }

    private static void reflect(){
        Field tag = ReflectionUtils.findField(BeanTwo.class, "tag");
        tag.setAccessible(true);
        try {
            String o = (String)tag.get(BeanTwo.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void map(){
        HashMap hashMap=new HashMap(2);
        hashMap.put("aa","11");
        hashMap.get("aa");
    }

    private static void bitSet(){
        /**
         * 大小可动态改变, 取值为true或false的位集合。用于表示一组布尔标志。
         */
        BitSet bitSet=new BitSet();
        bitSet.set(1,true);
        boolean b = bitSet.get(2);
        System.out.println(b);

        /**
         * 使用场景：编码为数字，结果仅有true或false两种结果。
         * 方便进行两个集合判断是否满足或者是否存在--进行与 或 异或等操作--过滤
         */

        /**
         * 方法：
         *
         * public void set(int pos): 位置pos的字位设置为true。
         *
         * public void set(int bitIndex, boolean value) 将指定索引处的位设置为指定的值。
         *
         * public void clear(int pos): 位置pos的字位设置为false。
         *
         * public void clear() : 将此 BitSet 中的所有位设置为 false。
         *
         * public int cardinality() 返回此 BitSet 中设置为 true 的位数。
         *
         * public boolean get(int pos): 返回位置是pos的字位值。
         *
         * public void and(BitSet other): other同该字位集进行与操作，结果作为该字位集的新值。
         *
         * public void or(BitSet other): other同该字位集进行或操作，结果作为该字位集的新值。
         *
         * public void xor(BitSet other): other同该字位集进行异或操作，结果作为该字位集的新值。
         *
         * public void andNot(BitSet set) 清除此 BitSet 中所有的位,set - 用来屏蔽此 BitSet 的 BitSet
         *
         * public int size(): 返回此 BitSet 表示位值时实际使用空间的位数。
         *
         * public int length() 返回此 BitSet 的“逻辑大小”：BitSet 中最高设置位的索引加 1。
         *
         * public int hashCode(): 返回该集合Hash 码， 这个码同集合中的字位值有关。
         *
         * public boolean equals(Object other): 如果other中的字位同集合中的字位相同，返回true。
         *
         * public Object clone() 克隆此 BitSet，生成一个与之相等的新 BitSet。
         *
         * public String toString() 返回此位 set 的字符串表示形式。
         */
    }

    public void test1() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            arrayList.add(Integer.valueOf(i));
        }

        // 复现方法一
        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer.intValue() == 5) {
                arrayList.remove(integer);
            }
        }

        // 复现方法二
        for (Integer value : arrayList) {
            if (value.intValue() == 5) {
                arrayList.remove(value);
            }
        }

        // 解决办法
        iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer.intValue() == 5) {
                iterator.remove();
            }
        }

        /**
         * 集合遍历删除报：异常java.util.ConcurrentModificationException
         * 原因分析：
         *  整个ArrayList中修改modCount的方法比较多，有add、remove、clear、ensureCapacityInternal等，凡是设计到ArrayList对象修改的都会自增modCount属性。
         *  但却没有把modCount赋值给expectedModCount的操作。
         *
         *  Iterator中的ListItr实现add、remove、clear、ensureCapacityInternal等会有modCount赋值给expectedModCount的操作。
         *
         *  在创建Iterator的时候会将modCount赋值给expectedModCount，在遍历ArrayList过程中，没有其他地方可以设置expectedModCount了。
         *  当使用iterator.remove();中也会把modCount赋值给expectedModCount，再下次调用checkForComodification()进行校验时，就不会报错。
         * 在执行next方法时，遇到modCount != expectedModCount方法，导致抛出异常java.util.ConcurrentModificationException。
         *
         * 但是为什么要这么做呢？
         *      很明显这么做是为了阻止程序员在不允许修改的时候修改对象，起到保护作用，避免出现未知异常。
         *      Iterator 是工作在一个独立的线程中，并且拥有一个 mutex 锁。
         *      Iterator 被创建之后会建立一个指向原来对象的单链索引表，当原来的对象数量发生变化时，这个索引表的内容不会同步改变。
         *      当索引指针往后移动的时候就找不到要迭代的对象，所以按照 fail-fast 原则 Iterator 会马上抛出 java.util.ConcurrentModificationException 异常。
         *      所以 Iterator 在工作的时候是不允许被迭代的对象被改变的。但你可以使用 Iterator 本身的方法 remove() 来删除对象， Iterator.remove() 方法会在删除当前迭代对象的同时维护索引的一致性。
         */
    }

    private static void bitmapA(){
        /**
         * Byte(byte)=8bit,jvm中最小数据单位是8bit
         * 前提boolean占用一个bit位，最大是512M
         */

        //bitmap：下标映射值，数组中的值仅保存是否存在，及true和false
        //以bit数组的下标对应QQ号--关键是bitmap映射的思想
        boolean[] bitmap=new boolean[2^32-1];
        for(boolean b:bitmap){
            //遍历大文件
            //存在则该位置赋值为true，否则，false
        }

        for(int i=0;i<bitmap.length;i++){
            if(bitmap[i]){
                System.out.println("打印去重之后的值："+i);
            }
        }

    }
}
