package com.kuark.tool.advance.advance20201105util.excel.advance1;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rock
 * @detail excel响应上下文对象
 * @date 2021/4/25 11:19
 */
public class ExcelRespContext<S> {
    private List<S> contextList;

    public void addData(List<S> s){
        if(CollectionUtils.isEmpty(s)){
            return;
        }
        contextList.addAll(s);
    }

    public ExcelRespContext(){
        contextList=new ArrayList<>();
    }

    public List<S> getContextList(){
        return contextList;
    }
}
