package com.kuark.tool.base.designMode.strategy.example.impl;

import com.kuark.tool.base.designMode.strategy.example.VipInterface;

/**
 * @author caoqingyuan
 * @detail
 * @date 2018/9/12 15:10
 */
public class GoldVip implements VipInterface {
    @Override
    public void service() {
        System.out.println("GoldVip");
    }
}
