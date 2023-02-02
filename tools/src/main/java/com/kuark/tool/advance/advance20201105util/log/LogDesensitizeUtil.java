package com.kuark.tool.advance.advance20201105util.log;

import com.alibaba.fastjson.JSON;
import com.kuark.tool.advance.advance20201105util.log.desensitize.CryptoConvertConfig;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @author rock
 * @detail json串指定字段脱敏操作工具类
 * @date 2022/3/29 16:38
 */
public class LogDesensitizeUtil {
    public static void main(String[] args) {
        //单层json 验证好用的
        Student s1=new Student();
        s1.setAge(20);
        s1.setName("xiaohong");
        s1.setPhone("18511289654");
        s1.setCardNo("87548795621347835");
        Student s2=new Student();
        s2.setAge(21);
        s2.setName("xiaolan");
        s2.setPhone("18611288726");
        s2.setCardNo("9874563210");

        //多层嵌套json 验证好用的
        Group g=new Group();
        g.setGroupName("1year2group");
        g.setLocation("anhui");
        g.setNum(50L);
        g.setStudents(Arrays.asList(s1,s2));

        //
        String jsonStr= JSON.toJSONString(g);
        System.out.println("before:"+jsonStr);
        String convert = CryptoConvertConfig.getJsonConvertor().convert(jsonStr);
        System.out.println("after:"+convert);
    }

    @Data
    public static class Student{
        private String name;
        private Integer age;
        private String phone;
        private String cardNo;
    }

    @Data
    public static class Group{
        private String groupName;
        private String location;
        private Long num;
        private List<Student> students;
    }
}
