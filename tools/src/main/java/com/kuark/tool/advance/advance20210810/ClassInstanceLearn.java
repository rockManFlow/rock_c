package com.kuark.tool.advance.advance20210810;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author caoqingyuan
 * @detail 类初始化
 * @date 2022/5/24 17:14
 */
public class ClassInstanceLearn {
    //单态实例　一切问题皆由此行引起
    //1、先执行类初始化方法也是static（该类会调用初始化方法）
    //2、之后执行类的一些初始化工作（静态变量及代码块）

    //所以，最终才会仅打印了一条数据
    private  static  final  ClassInstanceLearn SINGLE_ENUM_RESOLVER =  new
            ClassInstanceLearn();
    /*MSGCODE->Category内存索引*/
    private static Map CODE_MAP_CACHE;
    static {
        CODE_MAP_CACHE = new HashMap();
//为了说明问题,我在这里初始化一条数据
        CODE_MAP_CACHE.put("0","北京市");
    }
    //private, for single instance
    private ClassInstanceLearn() {
//初始化加载数据 引起问题，该方法也要负点责任
        initEnums();
    }
    /**
     * 初始化所有的枚举类型
     */
    public static void initEnums() {
// ~~~~~~~~~问题从这里开始暴露 ~~~~~~~~~~~//
        if (null == CODE_MAP_CACHE) {
            System.out.println("CODE_MAP_CACHE为空,问题在这里开始暴露.");
            CODE_MAP_CACHE = new HashMap();
        }
        CODE_MAP_CACHE.put("1", "北京市");
        CODE_MAP_CACHE.put("2", "云南省");
//..... other code...
    }
    public Map getCache() {
        return Collections.unmodifiableMap(CODE_MAP_CACHE);
    }
    /**
     * 获取单态实例
     *
     * @return
     */
    public  static  ClassInstanceLearn getInstance() {
        return  SINGLE_ENUM_RESOLVER;
    }
    public  static  void  main(String[] args) {
        System.out.println(ClassInstanceLearn.getInstance().getCache());
    }
}
