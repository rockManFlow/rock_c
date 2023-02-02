package com.kuark.tool.advance.advance20200313;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author rock
 * @detail
 * @date 2020/4/2 11:10
 */
public class OptionalTest {
    public static void main(String[] args) {
        List<Long> validProductList=new ArrayList<>();
        validProductList.add(20L);
        validProductList.add(30L);
        validProductList.add(40L);
        Long productId=10L;
        Optional<Long> first = validProductList.stream().filter(validProduct -> validProduct.equals(productId)).findFirst();
        System.out.println(first);
        if(first.isPresent()){
            System.out.println("result="+first.get());
        }
    }
}
