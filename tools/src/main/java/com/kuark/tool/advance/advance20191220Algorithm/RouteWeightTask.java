package com.kuark.tool.advance.advance20191220Algorithm;

import com.kuark.tool.advance.advance20191220Algorithm.vo.WeightVo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author rock
 * @detail 根据权重值来随机筛选
 * @date 2020/1/3 14:33
 */
public class RouteWeightTask {
    public static void main(String[] args){
        Map<String,Integer> map=new HashMap<>(2);
        map.put("xiaohong",3);
        map.put("heihei",5);
        map.put("sss",2);
        String key = weightKey(map);
        System.out.println("key:"+key);
    }

    //一种
    public static WeightVo<Integer> weight(List<Integer> countList){
        List<WeightVo> weightVoList=new ArrayList<>();
        Integer sumCount =0;
        Integer currentCount=0;
        for(Integer count:countList){
            //计算总值
            sumCount+=count;
            //设置起始值和终止值
            WeightVo vo=new WeightVo();
            vo.setStartValue(currentCount);
            currentCount+=count;
            vo.setEndValue(currentCount);
            vo.setData(count);
            weightVoList.add(vo);
        }

        //产生随机数
        int offset = ThreadLocalRandom.current().nextInt(sumCount);
        System.out.println("offset:"+offset);
        WeightVo vo = weightVoList.stream().filter(weightVo -> {
            if (weightVo.getStartValue() <= offset && weightVo.getEndValue() >= offset) {
                return true;
            }
            return false;
        }).findFirst().get();
        return vo;
    }

    //权重另外一种思路实现---及概率问题
    public static String weightKey(Map<String,Integer> weightMap){
        //计算总值
        Integer sumCount =weightMap.values().stream().mapToInt(a->a).sum();

        //产生随机数
        int offset = ThreadLocalRandom.current().nextInt(sumCount);
//        System.out.println("offset:"+offset);
        Integer currentCount=0;
        Set<String> keys = weightMap.keySet();
        for(String k:keys){
            currentCount+=weightMap.get(k);
            if(offset<=currentCount){
                return k;
            }
        }
        return null;
    }

    //随机数
    public static void randomMethod(){
        //1、0到n-1之间的随机数  这个方法内部也是使用的Random这个类
        int v =(int) (Math.random() * 10);
        System.out.println("随机整数1："+v);
        //2、
        Random random=new Random();
        int anInt = random.nextInt(10);
        System.out.println("随机整数2："+anInt);
        //3、产生随机小写字符
        char c = (char) ('a' + Math.random() * ('z' - 'a' + 1));
        System.out.println("随机小写字符:"+c);
    }

    //循环：随机一个起始位，之后循环累加，取值循环来取
    public static void round(List<String> addressList,String key){
        ConcurrentMap<String, Integer> routeCountEachJob = new ConcurrentHashMap<String, Integer>();
        long CACHE_VALID_TIME = 0;
        // cache clear
        if (System.currentTimeMillis() > CACHE_VALID_TIME) {
            routeCountEachJob.clear();
            CACHE_VALID_TIME = System.currentTimeMillis() + 1000*60*60*24;
        }

        // count++
        Integer count = routeCountEachJob.get(key);
        count = (count==null || count>1000000)?(new Random().nextInt(100)):++count;  // 初始化时主动Random一次，缓解首次压力
        routeCountEachJob.put(key, count);

        String address = addressList.get(count%addressList.size());
    }

    /**
     * 最近最久未被使用
     * LinkedHashMap 当参数accessOrder为true时，只要我们使用get方式访问一次，就会把这个访问过的值移到集合最后，第一个是最久未被使用的
     * 默认是false，及按照放的顺序
     */
    public static void routeLRU(int jobId, List<String> addressList){
        ConcurrentMap<Integer, LinkedHashMap<String, String>> jobLRUMap = new ConcurrentHashMap<Integer, LinkedHashMap<String, String>>();
        long CACHE_VALID_TIME = 0;
        // cache clear
        if (System.currentTimeMillis() > CACHE_VALID_TIME) {
            jobLRUMap.clear();
            CACHE_VALID_TIME = System.currentTimeMillis() + 1000*60*60*24;
        }

        // init lru
        LinkedHashMap<String, String> lruItem = jobLRUMap.get(jobId);
        if (lruItem == null) {
            /**
             * LinkedHashMap
             *      a、accessOrder：ture=访问顺序排序（get/put时排序）；false=插入顺序排期；
             *      b、removeEldestEntry：新增元素时将会调用，返回true时会删除最老元素；可封装LinkedHashMap并重写该方法，
             *      比如定义最大容量，超出是返回true即可实现固定长度的LRU算法；
             */
            lruItem = new LinkedHashMap<String, String>(16, 0.75f, true);
            //todo 缺少时才会被放，防止覆盖
            jobLRUMap.putIfAbsent(jobId, lruItem);
        }

        // put new
        for (String address: addressList) {
            if (!lruItem.containsKey(address)) {
                lruItem.put(address, address);
            }
        }
        // remove old
        List<String> delKeys = new ArrayList<>();
        for (String existKey: lruItem.keySet()) {
            if (!addressList.contains(existKey)) {
                delKeys.add(existKey);
            }
        }
        if (delKeys.size() > 0) {
            for (String delKey: delKeys) {
                lruItem.remove(delKey);
            }
        }

        // load
        String eldestKey = lruItem.entrySet().iterator().next().getKey();
        String eldestValue = lruItem.get(eldestKey);
        System.out.println("最近最久未被使用的值："+eldestValue);
    }

    /**
     * 最不经常使用的----使用次数最少的
     */
    public static void routeLFU(int jobId, List<String> addressList){
        ConcurrentMap<Integer, HashMap<String, Integer>> jobLfuMap = new ConcurrentHashMap<Integer, HashMap<String, Integer>>();
        long CACHE_VALID_TIME = 0;
        // lfu item init
        HashMap<String, Integer> lfuItemMap = jobLfuMap.get(jobId);     // Key排序可以用TreeMap+构造入参Compare；Value排序暂时只能通过ArrayList；
        if (lfuItemMap == null) {
            lfuItemMap = new HashMap<String, Integer>();
            jobLfuMap.putIfAbsent(jobId, lfuItemMap);   // 避免重复覆盖
        }

        //当超出指定值或新值---初始为0
        //当有新值时，最好全部都重新开始
        for (String address: addressList) {
            if (!lfuItemMap.containsKey(address) || lfuItemMap.get(address) >1000000 ) {
                lfuItemMap.put(address, 0);  // 初始值
            }
        }

        //按照从小到大排序
        List<Map.Entry<String, Integer>> lfuItemList = new ArrayList<Map.Entry<String, Integer>>(lfuItemMap.entrySet());
        Collections.sort(lfuItemList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        //取第一个最小的值
        Map.Entry<String, Integer> addressItem = lfuItemList.get(0);
        String minAddress = addressItem.getKey();
        addressItem.setValue(addressItem.getValue() + 1);

        System.out.println("最少使用次数"+minAddress);
    }

    /**
     * 一致性hash
     * JDK里面提供了红黑树的代码实现TreeMap和TreeSet。红黑树是个平衡二叉树
     * TreeMap本身提供了一个tailMap(K fromKey)方法，支持从红黑树中查找比fromKey大的值的集合，但并不需要遍历整个数据结构。
     */
    public static String consistentHash(int jobId, List<String> addressList){
        //主机个数
        int VIRTUAL_NODE_NUM = 5;
        // ------A1------A2-------A3------
        // -----------J1------------------
        TreeMap<Long, String> addressRing = new TreeMap<Long, String>();
        for (String address: addressList) {
            for (int i = 0; i < VIRTUAL_NODE_NUM; i++) {
                long addressHash = hash("SHARD-" + address + "-NODE-" + i);
                addressRing.put(addressHash, address);
            }
        }

        long jobHash = hash(String.valueOf(jobId));

        SortedMap<Long, String> lastRing = addressRing.tailMap(jobHash);
        if (!lastRing.isEmpty()) {
            return lastRing.get(lastRing.firstKey());
        }
        return addressRing.firstEntry().getValue();
    }

    /**
     * get hash code on 2^32 ring (md5散列的方式计算hash值)
     * @param key
     * @return
     */
    private static long hash(String key) {
        // md5 byte
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not supported", e);
        }
        md5.reset();
        byte[] keyBytes = null;
        try {
            keyBytes = key.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unknown string :" + key, e);
        }

        md5.update(keyBytes);
        //计算MD5
        byte[] digest = md5.digest();

        // hash code, Truncate to 32-bits
        long hashCode = ((long) (digest[3] & 0xFF) << 24)
                | ((long) (digest[2] & 0xFF) << 16)
                | ((long) (digest[1] & 0xFF) << 8)
                | (digest[0] & 0xFF);

        long truncateHashCode = hashCode & 0xffffffffL;
        return truncateHashCode;
    }
}
