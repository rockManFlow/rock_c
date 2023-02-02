package com.kuark.tool.advance.advance20190815.observor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author rock
 * @detail 这是观察者模式
 * @date 2019/8/15 17:14
 */
public class Subject {

    private List<Observor> observorList= Collections.synchronizedList(new ArrayList<>());
    void register(Observor observor){
        observorList.add(observor);
    }

    public void publish(Object info){
        observorList.stream().forEach(observor -> {
            observor.notice(info);
        });
    }
}
