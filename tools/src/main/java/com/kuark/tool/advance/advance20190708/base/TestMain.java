package com.kuark.tool.advance.advance20190708.base;

import java.io.RandomAccessFile;
import java.util.Collections;

/**
 * @author rock
 * @detail
 * @date 2023/1/28 18:21
 */
public class TestMain {
    public static void main(String[] args) {
        NonExtendOne one=new NonExtendOne();
        one.getName();

        NonCover2 cover=new NonCover2();
        cover.show();
        cover.show2();
        cover.show3();
        NonCover.show3();

        NonExtendThird third=new NonExtendThird();
        third.show();
    }
}
