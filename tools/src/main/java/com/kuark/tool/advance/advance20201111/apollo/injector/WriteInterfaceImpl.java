package com.kuark.tool.advance.advance20201111.apollo.injector;

/**
 * @author rock
 * @detail
 * @date 2021/4/1 14:24
 */
public class WriteInterfaceImpl implements WriteInterface {
    @Override
    public void write(String name) {
        System.out.println("WriteInterfaceImpl name:"+name);
    }
}
