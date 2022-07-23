package com.rock.auth.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 扩展spring security核心对象类
 * detail 可以拥有多种GrantedAuthority授权信息
 */
public class UserInfoDetail extends User {
    public UserInfoDetail(String username, String password, Collection<? extends GrantedAuthority> authorities){
        super(username, password, true, true, true, true, authorities);
    }
}
