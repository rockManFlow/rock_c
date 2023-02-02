package com.kuark.tool.base.reptile;

import org.junit.Test;

/**
 * Created by cc on 2016/5/25.
 */
public class TestHtml {
    @Test
    public void test1(){
        String st="src=\"test.html\" class=\"cdcd.html\"";
        System.out.println("size="+st.charAt(st.indexOf("\"")));
    }
}
