package com.rock.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestServiceImpl implements TestService{


    /**
     * UserDetails u1 = User.withUsername("jianxiang1")
     *             .password("12345")
     *             //设置用户角色或权限
     *             .authorities("WRITE")
     *             .build();
     *  仅有DELETE角色的用户才能进到这个方法中，其他的会报异常
     */
    @PreAuthorize(value = "hasAuthority('DELETE')")
    @Override
    public String showName() {
        return null;
    }

    /**
     * 使用注解实现方法级别过滤
     * 针对方法级别过滤，Spring Security 同样提供了一对注解，即分别用于预过滤和后过滤的 @PreFilter 和 @PostFilter。
     */
}
