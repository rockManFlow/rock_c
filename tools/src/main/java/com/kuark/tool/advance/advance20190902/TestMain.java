package com.kuark.tool.advance.advance20190902;

import com.kuark.tool.advance.advance20190902.vo.TypeEnum;
import com.kuark.tool.advance.advance20190902.vo.UserA;
import com.kuark.tool.advance.advance20190902.vo.UserB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rock
 * @detail 参数映射-不同用法使用
 * @date 2019/9/2 14:56
 */
public class TestMain {

    public static void main(String[] args){
        UserA a=new UserA();
        a.setAge(20);
        a.setDesc("测试A");
        a.setName("A用户");
        a.setTypeEnum(TypeEnum.ANHUI);
        MyMapperFacade mapperFacade=new MyMapperFacade();
        //不同参数名的映射
        Map<String,String> fieldMap=new HashMap<>();
        fieldMap.put("name","nameB");
        fieldMap.put("age","ageB");
        List<String> excludeFieldList=new ArrayList<>();
        //排除source参数名
        excludeFieldList.add("desc");
        UserB map = mapperFacade.map(a, UserB.class,fieldMap,excludeFieldList);
        System.out.println("mapB=  "+map);
    }

}
