package com.kuark.tool.base.test;

import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by cc on 2016/5/31.
 */
public class TestJar {
    public static void main(String[] str) throws InterruptedException, IOException {
        System.out.print("输入地址：");
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        System.out.println("read="+read);
        System.out.println();
        for(int i=0;i<10;i++){
            System.out.println("ff="+i);
            Thread.sleep(5000);
        }
        System.out.print("end执行main");
    }
    @Test
    public void test(){
        HashSet<String> cardInfos = new HashSet<String>();
        String st=cardInfos.iterator().next();
        System.out.println("st="+st);
    }
}
