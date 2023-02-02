package com.kuark.tool.advance.advance20191114.arithmetic.googleInject;

import com.kuark.tool.advance.advance20191114.arithmetic.googleInject.Manager;

/**
 * @author rock
 * @detail
 * @date 2019/11/20 17:14
 */
public class ManagerImpl implements Manager {
    @Override
    public String show() {
        System.out.println("manager show");
        return "M";
    }
}
