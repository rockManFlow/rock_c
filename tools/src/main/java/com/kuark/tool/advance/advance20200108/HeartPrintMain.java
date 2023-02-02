package com.kuark.tool.advance.advance20200108;

import java.util.stream.IntStream;

/**
 * @author caoqingyuan
 * @detail todo 控制台打印心形图案
 * @date 2020/4/15 14:24
 */
public class HeartPrintMain {
    public static void main(String[] args) {
        IntStream.range(-15, 15)
                .map(y -> -y)
                .forEach(y ->
                        IntStream.range(-30, 30)
                        .forEach(x ->
                                System.out.print(Math.pow(Math.pow((x * 0.05), 2)
                                        + Math.pow((y * 0.1), 2) - 1, 3) - Math.pow(x * 0.05, 2) * Math.pow(y * 0.1, 3) <= 0
                                        ? "love".charAt(Math.abs((y - x) % 4)) :
                                        " " + (x == 29 ? "\n" : ""))));
    }
}
