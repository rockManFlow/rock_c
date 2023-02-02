package com.kuark.tool.advance.advance20190907;

/**
 * @author rock
 * @detail 序列化问题
 * @date 2019/9/7 15:07
 */
public class SerializeTest {
    public static void main(String[] args){

    }

    public static void serial(){
        //经过transient修饰的信息是没法保存到文件中，不参与序列化（通过直接写对象的方法写入不了）
        //在网络传输中io中也不会被传输出去
    }
}
