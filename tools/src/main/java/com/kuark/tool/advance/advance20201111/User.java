package com.kuark.tool.advance.advance20201111;

import java.io.Serializable;

/**
 * @author rock
 * @detail
 * @date 2021/1/4 15:08
 */
public class User implements Serializable {
    private static final long serialVersionUID = -8088742348807697485L;

    private String userName;

    public User() {
        System.out.println("call construct method");
    }

    public String getUserName() {
        System.out.println("call get method getUserName");
        return userName;
    }

    public void setUserName(String userName) {
        System.out.println("call set  method setUserName");
        this.userName = userName;
    }
}
