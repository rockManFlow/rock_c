package com.kuark.tool.advance.advance20191005;

import lombok.extern.log4j.Log4j;

/**
 * @author rock
 * @detail
 * @date 2019/11/12 16:55
 */
@Log4j
public class FacadeImpl1 implements Facade{
    /**
     *
     * @param name
     * @return
     */
    public String show(String name){
        log.info("FacadeImpl1 show value "+name);
        return name+"FacadeImpl1";
    }

    public Object write(Integer age,String desc){
        log.info("FacadeImpl1 write "+age+"|"+desc);
        return "FacadeImpl1 OK";
    }
}
