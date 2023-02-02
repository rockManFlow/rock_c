package com.kuark.tool.advance.advance20201111.apollo;

/**
 * @author rock
 * @detail
 * @date 2022/3/23 18:40
 */
public class ServiceInterfaceImpl implements ServiceInterface {
    @Override
    public String show() {
        System.out.println("ServiceInterfaceImpl show");
        return "show OK";
    }
}
