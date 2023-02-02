package com.kuark.tool.advance.advance20191220Algorithm;

/**
 * @author rock
 * @detail
 * @date 2020/4/7 14:43
 */

/**
 * 雪花算法：雪花算法生成的ID是纯数字且具有时间顺序的
 * 首位无效符、时间戳差值，机器(进程)编码，序列号四部分组成 -64位
 *
 时间位：可以根据时间进行排序，有助于提高查询速度。
 机器id位：适用于分布式环境下对多节点的各个节点进行标识，可以具体根据节点数和部署情况设计划分机器位10位长度，如划分5位表示进程位等。
 序列号位：是一系列的自增id，可以支持同一节点同一毫秒生成多个ID序号，12位的计数序列号支持每个节点每毫秒产生4096个ID序号

 是 Twitter 开源的分布式 id 生成算法。其核心思想就是：使用一个 64 bit 的 long 型的数字作为全局唯一 id。
 在分布式系统中的应用十分广泛，且ID 引入了时间戳，基本上保持自增的，后面的代码中有详细的注解。

 这 64 个 bit 中，其中 1 个 bit 是不用的，然后用其中的 41 bit 作为毫秒数，用 10 bit 作为工作机器 id，12 bit 作为序列号。

 {@link com.kuark.tool.advance.advance20200313.SerialNumberServiceImpl}
 */
public class Snowflake {
    public static void main(String[] args) {
        //时间为+分布式节点标识+序列号
        //SerialNumberServiceImpl就是一个雪花算法的实现
    }
}
