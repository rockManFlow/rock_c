package com.kuark.tool.base.designMode.strategy;

import com.kuark.tool.base.designMode.strategy.example.AbstractServiceHander;
import com.kuark.tool.base.designMode.strategy.example.VipInterface;
import com.kuark.tool.base.designMode.strategy.example.impl.GoldVip;
import com.kuark.tool.base.designMode.strategy.example.impl.SuperVip;
import com.kuark.tool.base.designMode.strategy.example.impl.UnVip;
import com.kuark.tool.base.designMode.strategy.example.impl.Vip;

import java.util.Map;
import java.util.Set;

/**
 * @author caoqingyuan
 * @detail
 * @date 2018/9/12 14:52
 */
public class Example1 {
    /**
     * 策略模式：定义了一系列的算法，将每个算法封装起来，使他们可以相互替换，让算法独立于他的客户端而存在
     * 解决使用if else或case等问题,会涉及到策略的选择
     */
    /**
     * 计算各个区间，各个区间使用不同的处理模式
     * 每个充值区间，都有不同的服务
     *
     * 设计三种角色：具体使用地方的引用(上下文)、角色给出所有策略所需的接口(抽象)、各个策略接口的实现(具体)
     */
    public VipInterface badMethod(Double totalAmount){
        if(totalAmount>30000){
            return new GoldVip();
        }else if(totalAmount>20000){
            return new SuperVip();
        }else if(totalAmount>10000){
            return new Vip();
        }else{
            return new UnVip();
        }
    }

    public VipInterface middleMethod(Double totalAmount){
        Set<Double> keySet = AbstractServiceHander.serviceHander.keySet();
        Double middleKey=AbstractServiceHander.serviceHander.firstKey();
        for(Double key:keySet){
            if(totalAmount>key){
                middleKey=key;
                continue;
            }
            break;
        }
        return AbstractServiceHander.serviceHander.get(middleKey);
    }

    public static void main(String[] args){
        Example1 e=new Example1();
        VipInterface vip=e.middleMethod(10010D);
        vip.service();
        vip=e.badMethod(10010D);
        vip.service();
    }
}
