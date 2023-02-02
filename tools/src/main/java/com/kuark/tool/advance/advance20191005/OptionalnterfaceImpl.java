package com.kuark.tool.advance.advance20191005;

import lombok.extern.log4j.Log4j;

/**
 * @author rock
 * @detail
 * @date 2019/11/14 20:59
 */
@Log4j
public class OptionalnterfaceImpl implements Optionalnterface {
    @Override
    public String show(String name) {
        log.info("show name="+name);
        return "OK"+name;
    }

    @Override
    public void write(Integer age) {
        log.info("write age="+age);
    }

    @Override
    public Integer pull() {
        log.info("pull");
        return 11;
    }
}
