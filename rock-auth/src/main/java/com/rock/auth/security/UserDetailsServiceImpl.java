package com.rock.auth.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * service扩展操作数据库
 */
@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //在这个里面执行自定义查询用户信息逻辑--比如从数据库中查询数据

        log.info("UserDetailsServiceImpl loadUserByUsername username:{}",username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new UserInfoDetail(username,"123456",authorities);
    }
}
