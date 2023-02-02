package com.kuark.tool.base.designMode.strategy.example.impl;

import com.kuark.tool.base.designMode.strategy.example.VipInterface;

/**
 * @author caoqingyuan
 * @detail
 * @date 2018/9/12 15:14
 */
public class UnVip implements VipInterface {
    @Override
    public void service() {
        System.out.println("UnVip");
    }
}
