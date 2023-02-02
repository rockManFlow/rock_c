package com.kuark.tool.advance.advance20191220Algorithm;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author rock
 * @detail 一致性hash实现----适用于缓存
 * @date 2021/3/10 20:42
 */
public class ConsistencyHash {
    //每个key分成多少个虚次，使数据更均匀
    private static int count = 30;

    private static TreeMap<Long, String> nodeMap = new TreeMap<>();

    public static void main(String[] args) {
        String[] cacheServers = {"192.168.56.101:11211", "192.168.56.102:11211", "192.168.56.103:11211"};
        for (int i = 0; i < cacheServers.length; i++) {
            addNode(cacheServers[i]);
        }

        String n = getNode("judheuhudehsidis");
        String n2 = getNode("judheuhudehsidis");
        String n3 = getNode("12456kijuhgy");
        System.out.println(n);
        System.out.println(n2);
        System.out.println(n3);
    }

    public static long hash(String key) {
        String md5key = DigestUtils.md5Hex(key);
        //在2的64次方上取余
        return Long.parseLong(md5key.substring(0, 15), 16) % ((long) Math.pow(2, 64));
    }

    public static void addNode(String node) {
        for (int i = 0; i < count; i++) {
            long hash = hash(node + i);
            nodeMap.put(hash, node);
        }
    }

    public static String getNode(String key) {
        long hash = hash(key);
        //获取比该hash大的一个map值
        SortedMap<Long, String> longStringSortedMap =
                nodeMap.tailMap(hash);
        long firstKey = longStringSortedMap.isEmpty() ? nodeMap.firstKey() : longStringSortedMap.firstKey();
        return nodeMap.get(firstKey);
    }

    /**
     * Guava Cache 框架支持一致性哈希
     */
    public static void guavaConsitHash() {
        //实体缓存服务器
        String[] cacheServers = {"192.168.56.101:11211", "192.168.56.102:11211", "192.168.56.103:11211"};

        // 缓存数据的key
        String key = "my-test-cache-key";

        // 计算缓存 key 对应的 hash 值，这里使用 MurmurHash 算法，MurmurHash 是一种高性能低碰撞的算法。此外，还支持  md5、sha1/sha256/sha512、orc32、adler32 等哈希算法。
        HashCode hashCode = Hashing.murmur3_32().newHasher().putString(key, Charsets.UTF_8).hash();


        // 通过一致性哈希方式计算，缓存key对应的服务器主机是那一台，bucket 的范围在 0 ~ cacheServers.length -1
        int bucket = Hashing.consistentHash(hashCode, cacheServers.length);
    }
}
