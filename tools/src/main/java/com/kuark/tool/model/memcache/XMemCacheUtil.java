package com.kuark.tool.model.memcache;//package com.model.memcache;
//
//import net.rubyeye.xmemcached.MemcachedClient;
//import net.rubyeye.xmemcached.MemcachedClientBuilder;
//import net.rubyeye.xmemcached.XMemcachedClientBuilder;
//import net.rubyeye.xmemcached.exception.MemcachedException;
//import net.rubyeye.xmemcached.utils.AddrUtil;
//import org.apache.log4j.Logger;
//import org.dom4j.Document;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//import sun.security.provider.MD5;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.SortedMap;
//import java.util.TreeMap;
//import java.util.concurrent.TimeoutException;
//
///**
// * Created by caoqingyuan on 2016/10/8.
// */
//public class XMemCacheUtil {
//    private static Logger logger = Logger.getLogger(XMemCacheUtil.class);
//
//    private final String ConfigFile = "properties/memcache.xml";
//
//    protected static TreeMap<Integer, MemcachedClient> mcMap = new TreeMap<Integer, MemcachedClient>();//虚拟节点对应的真实节点
//    protected static List<MemcachedClient> mcList = new ArrayList<MemcachedClient>();//所有的真实节点
//    protected static XMemCacheUtil xmemCacheUtil = new XMemCacheUtil();// 创建全局的唯一实例
//    protected static final int NODE_NUM = 100;
//    protected static final int DEFAULT_TIME = 5 * 60 * 60;
//
//    /**
//     * 获取唯一实例.
//     * @return
//     */
//    public static XMemCacheUtil getInstance() {
//        if (xmemCacheUtil != null) {
//            return xmemCacheUtil;
//        } else {
//            xmemCacheUtil = new XMemCacheUtil();
//            xmemCacheUtil.init();
//            return xmemCacheUtil;
//        }
//    }
//
//    // 设置与缓存服务器的连接池
//    @SuppressWarnings("unchecked")
//    public boolean init() {
//
//        //读取配置文件，生成真实节点
//        File file = new File(this.getClass().getResource("/").getPath() + ConfigFile);
//
//        if (!file.exists()) {
//            logger.error("NOT FOUND memcached config file!");
//            return false;
//        }
//
//        SAXReader reader = new SAXReader();
//        Document doc;
//        try {
//            doc = reader.read(file);
//
//            Element root = doc.getRootElement();
//
//            Element memcaches = root.element("memcaches");
//            if (null != memcaches) {
//                List<Element> memcacheList = memcaches.elements("memcache");
//                if (null != memcacheList) {
//                    for (Element memcache : memcacheList) {
//                        String memcacheHost = memcache.element("memcacheHost").getTextTrim();
//                        MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(memcacheHost));
//                        MemcachedClient memcachedClient = builder.build();
//                        mcList.add(memcachedClient);
//                    }
//                }
//            }
//        } catch (Exception e) {
//            logger.error("init memcache error" + e);
//            return false;
//        }
//
//        //生成虚拟节点
//        for (int i = 0; i != mcList.size(); ++i) { // 每个真实机器节点都需要关联虚拟节点,虚拟节点越多，分布越均衡
//            final MemcachedClient client = mcList.get(i);
//            for (int n = 0; n < NODE_NUM; n++) {// 一个真实机器节点关联NODE_NUM个虚拟节点
//                mcMap.put(MD5.getInstance().encode("SHARD-" + i + "-NODE-" + n).hashCode(), client);
//            }
//        }
//
//        return true;
//    }
//
//    /**
//     * 返回该虚拟节点对应的真实机器节点的信息
//     */
//    private MemcachedClient getMc(String key) {
//        SortedMap<Integer, MemcachedClient> tail = mcMap.tailMap(MD5.getInstance().encode(key).hashCode()); // 沿环的顺时针找到一个虚拟节点
//        if (tail.size() == 0) {
//            return mcMap.get(mcMap.firstKey());
//        }
//        return tail.get(tail.firstKey()); // 返回该虚拟节点对应的真实机器节点的信息
//    }
//
//    public boolean set(String key, Object value) {
//        try {
//            return getMc(key).set(key, DEFAULT_TIME, value);
//        } catch (Exception e) {
//            logger.error("xmemcache set error" + e);
//            return false;
//        }
//    }
//
//    public boolean setForHour(String key, Object value, int hours) {
//        try {
//            return getMc(key).set(key, hours * 3600, value);
//        } catch (Exception e) {
//            logger.error("xmemcache set error" + e);
//            return false;
//        }
//    }
//
//    public boolean setForMin(String key, Object value, int mins) {
//        try {
//            return getMc(key).set(key, mins * 60, value);
//        } catch (Exception e) {
//            logger.error("xmemcache set error" + e);
//            return false;
//        }
//    }
//
//    public Object get(String key) {
//        try {
//            return getMc(key).get(key);
//        } catch (Exception e) {
//            logger.error("xmemcache get error" + e);
//            return null;
//        }
//    }
//
//    public void delete(String key) {
//        try {
//            getMc(key).delete(key);
//        } catch () {
//            logger.error("xmemcache delete error" + e);
//        }
//    }
//}
