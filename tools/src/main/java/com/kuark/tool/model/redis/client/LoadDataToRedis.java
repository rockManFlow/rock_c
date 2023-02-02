package com.kuark.tool.model.redis.client;

import net.sf.json.JSONObject;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * 存储指定的数据类型
 * Created by caoqingyuan on 2017/7/6.
 */
public class LoadDataToRedis {
    //TODO 对象放到hash数据结构中===关键是key和fieldKey和value怎么来分配
    /**
     * TODO Hash数据类型
     * 把一个对象放到redis中，底层存储的数据类型为Hashmap，底层是key-value形式
     *
     * 底层存储结构具体为：
     * key对应数据主键key
     * value为field属性标签key+value属性值
     * @param key
     * @param fieldKey
     * @param obj
     */
    public static <T> void loadObjectToRedis(String key, String fieldKey,T obj) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = RedisPoolFactory.getInstance();
            jedis = pool.getResource();
            JSONObject json = JSONObject.fromObject(obj);
            jedis.hset(key, fieldKey, json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(jedis!=null){
                pool.returnResource(jedis);//把这个返回到池中
            }
        }
    }

    /**
     * TODO　hash数据类型中
     * 把list集合放到
     * 特点key--hash(fieldKey,value)
     * @param key
     * @param fieldKeyName
     * @param list
     * @param <T>
     */
    public static <T> void loadListToRedis(String key,String fieldKeyName,List<T> list) {
        JedisPool jedisPool = null;
        Jedis jedis = null;
        try {
            jedisPool = RedisPoolFactory.getInstance();
            jedis = jedisPool.getResource();
            for (T t : list) {
                JSONObject jsonObject = JSONObject.fromObject(t);
                //通过反射获取字段的值
                Field field = getField(t.getClass(), fieldKeyName);
                jedis.hset(key, field.get(t).toString(), jsonObject.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 通过反射获取某个类的属性
     *
     * @param clazz
     * @param name
     * @return
     * @throws Exception
     */
    private static Field getField(Class<?> clazz, String name) throws Exception {
        Field field = null;
        for (Field f : clazz.getDeclaredFields()) {
            if (f.getName().equals(name)) {
                f.setAccessible(true);
                field = f;
            }
        }
        return field;
    }

    public static Object getObjectFromRedis(String key, String fieldKey, Class clz) {
        Object obj = null;
        JedisPool jedisPool = null;
        Jedis jedis = null;
        try {
            jedisPool = RedisPoolFactory.getInstance();
            jedis = jedisPool.getResource();
            String str = jedis.hget(key, fieldKey);
            JSONObject jsonObject = JSONObject.fromObject(str);
            obj = JSONObject.toBean(jsonObject, clz);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return obj;
    }

    //TODO string数据类型的操作
    //Strings 数据结构是简单的key-value类型，value其实不仅是String，也可以是数字.
    //常用命令:  set,get,decr,incr,mget 等。

    //当个对象以string类型来存储
    public static <T> void loadObjectToRedisString(String key,T valueObj){
        JedisPool pool= null;
        try {
            pool = RedisPoolFactory.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Jedis jedis=pool.getResource();
        JSONObject value= JSONObject.fromObject(valueObj);
        jedis.set(key,value.toString());
        pool.returnResource(jedis);
    }
    public static Object getObjectFromRedisString(String key,Class cla){
        JedisPool pool= null;
        try {
            pool = RedisPoolFactory.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Jedis jedis=pool.getResource();
        JSONObject value= JSONObject.fromObject(jedis.get(key));
        Object ob= JSONObject.toBean(value,cla);
        pool.returnResource(jedis);
        return ob;
    }

    //TODO list数据类型
    //常用命令：lpush,rpush,lpop,rpop,lrange等。
    //以一个双向链表的形式来实现的,操作也是链表的放和取
    public static <T> void loadObjectToRedisList(String key,T obj){
        JedisPool pool= null;
        try {
            pool = RedisPoolFactory.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Jedis jedis=pool.getResource();
        JSONObject json=JSONObject.fromObject(obj);
        jedis.lpush(key,json.toString());//放到链表的头。rpush放到链表的尾
        pool.returnResource(jedis);
    }
    public static Object getObjectFromRedisList(String key,Class cla){
        JedisPool pool= null;
        try {
            pool = RedisPoolFactory.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Jedis jedis=pool.getResource();
        String value=jedis.lpop(key);
        pool.returnResource(jedis);
        return JSONObject.toBean(JSONObject.fromObject(value),cla);
    }
    public static void main(String[] s){
        //把一个对象放到redis数据类型hash中
//        User user = new User();
//        user.setId(11);
//        user.setName("xiaohong");
//        user.setAge(18);
//        user.setAddr("小溪流");
//        loadObjectToRedis(String.valueOf(user.getId()),"id",user);
//        User newUser = (User)getObjectFromRedis(String.valueOf(user.getId()),"id",User.class);
//        System.out.println(newUser);
        //把list结合放到hash数据类型中
//        List<User> useList = new ArrayList<User>();
//        for (int i = 0; i < 10; i++) {
//            User o = new User();
//            o.setId(i);
//            o.setName("xiaohong" + i);
//            o.setAge(i);
//            o.setAddr("小溪流"+i);
//            useList.add(o);
//        }
//        loadListToRedis("userList", "id", useList);
//        User newUser = (User) getObjectFromRedis("userList", "7", User.class);
//        System.out.println(newUser);

        //当个对象以string来存放
        User userStr = new User();
        userStr.setId(11);
        userStr.setName("xiaotongtong");
        userStr.setAge(20);
        userStr.setAddr("小溪流");
        loadObjectToRedisString(String.valueOf(userStr.getId()),userStr);
        User lastUser=(User)getObjectFromRedisString(String.valueOf(userStr.getId()),User.class);
//        if(lastUser!=null){
//            //执行删除
//        }
        System.out.println("lastUser:"+lastUser);

        //底层以链表的形式list来存储key+value
//        User userNew = new User();
//        userNew.setId(22);
//        userNew.setAddr("小桥");
//        userNew.setAge(12);
//        userNew.setName("json");
//        loadObjectToRedisList(String.valueOf(userNew.getId()),userNew);
//        Object redisList = getObjectFromRedisList(String.valueOf(userNew.getId()), User.class);
//        System.out.println("newUser:"+redisList);

        //TODO 还有Set+Sorted Set+Pub/Sub+Transactions 应该都相似

    }


    //TODO 设置某个键的超时时间
    @Test
    public void judge(){
        JedisPool pool= null;
        try {
            pool = RedisPoolFactory.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Jedis jedis=pool.getResource();
        jedis.set("111","ceshi");
        jedis.expire("111",3);//设置某个键的超时时间
        jedis.set("222","ceshi222");
        jedis.expire("222",6);
        System.out.println("kk:"+jedis.get("111"));
        System.out.println("ss:"+jedis.get("222"));
        try {
            Thread.sleep(4*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("kk2:"+jedis.get("111"));
        System.out.println("ss2:"+jedis.get("222"));

//        jedis.flushAll();
    }
}
