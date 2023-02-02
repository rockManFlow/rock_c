package com.kuark.tool.base.designMode.strategy.example;

import com.kuark.tool.base.designMode.strategy.example.impl.GoldVip;
import com.kuark.tool.base.designMode.strategy.example.impl.SuperVip;
import com.kuark.tool.base.designMode.strategy.example.impl.UnVip;
import com.kuark.tool.base.designMode.strategy.example.impl.Vip;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author caoqingyuan
 * @detail
 * @date 2018/9/12 15:22
 */
public abstract class AbstractServiceHander {
    public static TreeMap<Double,VipInterface> serviceHander=new TreeMap<>();
    static {
        serviceHander.put(30000D,new GoldVip());
        serviceHander.put(20000D,new SuperVip());
        serviceHander.put(10000D,new Vip());
        serviceHander.put(5000D,new UnVip());
    }
}
