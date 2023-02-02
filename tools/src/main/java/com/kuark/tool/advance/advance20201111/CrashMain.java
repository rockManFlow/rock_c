package com.kuark.tool.advance.advance20201111;

import sun.misc.Unsafe;

/**
 * @author rock
 * @detail
 * @date 2021/1/21 10:10
 */
public class CrashMain {
    public static void main(String[] argv) {
        try {
            recur();
        }
        catch (Error e) {
            System.out.println(e.toString());
        }
        System.out.println("Ended normally");
    }
    static void recur() {
        Object[] o = null;
        try {
            while(true) {
                Object[] newO = new Object[1];
                newO[0] = o;
                o = newO;
            }
        }
        finally {
            recur();
        }
    }
}
