package com.kuark.tool.base.test;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by caoqingyuan on 2017/4/26.
 */
public class TestMain {

    public static void main(String[] args) {
        long l = System.nanoTime();
        System.out.println(l);
    }

    /**
     * 调用次数为2n，时间复杂度为O(n)
     */
    private static void time(){
        Integer[] num=new Integer[]{5,4,1,9,3,18};
        Integer d=10;
        double mid=(d/2.0);
        System.out.println("mid:"+mid);
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<num.length;i++){
            if(mid==(double)num[i]){
                Integer m=map.get(mid);
                Integer sum=num[i];
                if(m!=null){
                    sum=num[i]+m;
                }
                map.put(num[i],sum);
            }else{
                map.put(num[i],num[i]);
            }
        }
        Set<Integer> set = map.keySet();
        for(Integer key:set){
            if(key>d){
                continue;
            }
            int m=d-map.get(key);
            if(map.get(m)!=null){
                System.out.println("A:"+key+"|B:"+m);
            }
        }
    }
}
