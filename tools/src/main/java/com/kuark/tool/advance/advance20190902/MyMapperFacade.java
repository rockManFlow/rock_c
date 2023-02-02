package com.kuark.tool.advance.advance20190902;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;

import java.util.List;
import java.util.Map;

/**
 * @author rock
 * @detail 实体映射优化
 * @date 2019/9/2 15:16
 */
public class MyMapperFacade {
    private static MapperFactory mapperFactory;
    private ClassMapBuilder classMapBuilder;

    public MyMapperFacade(){
        if(mapperFactory==null){
            mapperFactory = new DefaultMapperFactory.Builder().build();
        }
    }

    private MapperFacade getMapperFacade(Class ca, Class cb){
        classMapBuilder=mapperFactory.classMap(ca,cb);
        return mapperFactory.getMapperFacade();
    }

    //相同参数名的映射
    public <S, D> D map(S var1, Class<D> var2){
        return getMapperFacade(var1.getClass(),var2).map(var1,var2);
    }

    //不同参数名映射
    public <S, D> D map(S var1, Class<D> var2, Map<String,String> fieldMap){
        return getDifferentFieldNameMapperFacade(var1.getClass(),var2,fieldMap).map(var1,var2);
    }

    //不同参数名映射+排除指定参数
    public <S, D> D map(S var1, Class<D> var2, Map<String,String> fieldMap, List<String> excludeFieldList){
        return getExcludeDifferentFieldNameMapperFacade(var1.getClass(),var2,fieldMap,excludeFieldList).map(var1,var2);
    }

    //不同参数名映射
    public MapperFacade getDifferentFieldNameMapperFacade(Class ca, Class cb, Map<String,String> fieldMap){
        classMapBuilder=mapperFactory.classMap(ca,cb);
        if(fieldMap!=null&&!fieldMap.isEmpty()){
            fieldMap.keySet().stream().forEach(fieldKey->{
                classMapBuilder.field(fieldKey,fieldMap.get(fieldKey));
            });
        }
        classMapBuilder.byDefault().register();
        return mapperFactory.getMapperFacade();
    }

    //不同参数名映射+排除指定参数映射
    public MapperFacade getExcludeDifferentFieldNameMapperFacade(Class ca, Class cb, Map<String,String> fieldMap, List<String> excludeFieldList){
        classMapBuilder=mapperFactory.classMap(ca,cb);
        if(excludeFieldList!=null&&!excludeFieldList.isEmpty()){
            excludeFieldList.stream().forEach(excludeField->{
                classMapBuilder.exclude(excludeField);
            });
        }

        if(fieldMap!=null&&!fieldMap.isEmpty()){
            fieldMap.keySet().stream().forEach(fieldKey->{
                classMapBuilder.field(fieldKey,fieldMap.get(fieldKey));
            });
        }
        classMapBuilder.byDefault().register();
        return mapperFactory.getMapperFacade();
    }
}
