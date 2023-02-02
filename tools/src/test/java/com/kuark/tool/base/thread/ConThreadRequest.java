package com.kuark.tool.base.thread;

/**
 * @author caoqingyuan
 * @detail 并发请求一个接口
 * @date 2019/5/18 10:03
 */
public class ConThreadRequest {
    public static void main(String[] args){
        //首先查询未锁的token数据并且不为空
        //其次、进行互斥校验
        //校验不通过，或者失效，进行加锁
        //加锁之后，进行token的获取并更新，同时解锁
        //进行互斥校验
        //如果加锁失败，则返回处理中，等待补偿
    }
}
