package com.kuark.tool.base.javaAPI.jdk18;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by caoqingyuan on 2017/11/8.
 */
public class MyStream {
    public static void main(String[] args){
        List<Student> lists = new ArrayList<>();
        lists.add(new Student(6,"赵9","女"));
        lists.add(new Student(4,"赵7","女"));
        lists.add(new Student(5,"赵8","女"));
        lists.add(new Student(2,"李四","男"));
        lists.add(new Student(3,"赵六","女"));
        lists.add(new Student(7,"赵10","女"));
        lists.add(new Student(1,"张三","男"));

        //由性别组成的流--提取数据
        Stream<String> sexyStream = lists.stream().map((Student s) -> s.getSexy());
        //把这个流还原成list集合
        List<String> stList=sexyStream.collect(Collectors.toList());

        Stream<Student> stream = lists.stream();

        //筛选
        //返回由给定谓词组成的流--其中test方法就是判断条件，true 放到流中  false 去掉
        Stream<Student> boyStream=stream.filter((Student s)-> s.getSexy().equals("男"));
        System.out.println("男的人数:"+boyStream.count());

        //返回一个int元素组成的流对象
        IntStream is = lists.stream().mapToInt((Student s)->s.getId());
        System.out.println("ID编号之和为:"+is.sum());

        Student st=lists.stream().min((p1, p2) -> {
            if (p1.getId() > p2.getId()) {
                return 1;
            } else {
                return -1;
            }
        }).get();
        System.out.println(st.getId()+"|"+st.getName());
    }
    static class Student{
        Integer id;
        String name;
        String sexy;

        public Student(Integer id, String name, String sexy) {
            super();
            this.id = id;
            this.name = name;
            this.sexy = sexy;
        }
        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getSexy() {
            return sexy;
        }
        public void setSexy(String sexy) {
            this.sexy = sexy;
        }
    }
}
