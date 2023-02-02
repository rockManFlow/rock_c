package com.kuark.tool.advance.advance20200108;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author rock
 * @detail
 * @date 2020/1/9 14:46
 */
public class StreamMain {

    public static void main(String[] args){
        //根据学生名进行分组
        List<Student> stuList=null;
        Map<String, List<Student>> collect = stuList.stream().collect(Collectors.groupingBy(Student::getName));
    }
}
