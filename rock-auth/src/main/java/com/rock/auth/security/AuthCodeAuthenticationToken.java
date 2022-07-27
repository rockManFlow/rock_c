package com.rock.auth.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义AuthenticationToken类型
 */
public class AuthCodeAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public AuthCodeAuthenticationToken(String username,String code){
        super(username,code);
    }
}
