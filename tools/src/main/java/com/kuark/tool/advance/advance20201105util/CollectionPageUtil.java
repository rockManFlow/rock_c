package com.kuark.tool.advance.advance20201105util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rock
 * @detail 集合进行分页
 * @date 2020/9/22 15:13
 */
public class CollectionPageUtil {
    /**
     *
     * list集合分页
     * @param list
     * @param pageNum  页码 从1开始
     * @param pageSize 每页多少条数据
     * @return
     */
    public static <T> List<T> startPage(List<T> list, Integer pageNum, Integer pageSize) {
        if (list == null||list.size() == 0) {
            return new ArrayList<T>(0);
        }

        // 记录总数
        Integer count = list.size();
        // 页数
        Integer pageCount = 0;
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }

        // 开始索引
        int fromIndex = 0;
        // 结束索引
        int toIndex = 0;

        if (pageNum<pageCount) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else if(pageNum.equals(pageCount)){
            fromIndex = (pageCount - 1) * pageSize;
            toIndex = count;
        }else{
            return new ArrayList<T>(0);
        }
        List<T> pageList = list.subList(fromIndex, toIndex);
        return pageList;
    }

    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        list.add("1111");
        list.add("2222");
        list.add("3333");
        list.add("4444");
        list.add("5555");
        List<String> page = startPage(list, 4, 2);
        System.out.println(page);
    }
}
