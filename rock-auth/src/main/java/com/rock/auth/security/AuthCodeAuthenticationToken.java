package com.rock.auth.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * 自定义AuthenticationToken类型
 */
public class AuthCodeAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public AuthCodeAuthenticationToken(String username,String code){
        super(username,code);
    }
}
