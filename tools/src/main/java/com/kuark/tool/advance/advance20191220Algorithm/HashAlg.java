package com.kuark.tool.advance.advance20191220Algorithm;

import com.google.common.hash.Hashing;

import java.util.*;

/**
 * @author rock
 * @detail 分表一致性hash算法的简单实现
 * @date 2020/2/27 15:34
 */
public class HashAlg {
    private static HashMap<Integer,String> hashMap=null;
    private static List<Integer> keysList=null;

    public static void main(String[] args){
        hash();
        String st="12986y537281";
        String st2="9837625321";
        String st3="76453421843";
        //计算key
        Integer key = getKey(st.getBytes());
        Integer key2 = getKey(st2.getBytes());
        Integer key3 = getKey(st3.getBytes());
        System.out.println("key1="+key+"key2="+key2+"key3="+key3);

        for(Integer ky: Arrays.asList(key,key2,key3)){
            for(int i=0;i<keysList.size();i++){
                if(keysList.get(i)>ky){
                    Integer keyName = keysList.get(i == 0 ? keysList.size() - 1 : i);
                    System.out.println("name="+hashMap.get(keyName)+"||key="+ky);
                    break;
                }
                if(i==keysList.size()-1){
                    Integer keyName = keysList.get(0);
                    System.out.println("name="+hashMap.get(keyName)+"||key="+ky);
                    break;
                }
            }
        }
    }
    public static void hash(){
        List<String> tableNameList=new ArrayList<>();
        tableNameList.add("voucher_0");
        tableNameList.add("voucher_1");
        tableNameList.add("voucher_2");
        Integer virtualBaseNum=5;
        keysList=new ArrayList<>();
        hashMap=new HashMap<>();
        for(int i=0;i<tableNameList.size();i++){
            for(int j=0;j<virtualBaseNum;j++){
                Integer hashKey= getKey((j+tableNameList.get(i)).getBytes());
                keysList.add(hashKey);
                hashMap.put(hashKey,tableNameList.get(i));
            }
        }
        Collections.sort(keysList);
    }

    public static Integer getKey(byte[] primaryKey) {
        return Hashing.murmur3_32().hashBytes(primaryKey).hashCode();
    }

    public static void stringHash(){
        //有自己的一套计算hashcode的方法
        String st="";
    }

}
