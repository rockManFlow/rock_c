package com.rock.auth.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 管理service进行验证
 */
@Service(value = "authenticationPasswordProvider")
@Slf4j
public class AuthenticationPasswordProviderImpl implements AuthenticationProvider {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("AuthenticationPassword authenticate");
        String username = (String) authentication.getPrincipal();
        String password = (String)authentication.getCredentials();

        UserDetails user = userDetailsService.loadUserByUsername(username);
        if (password.equals(user.getPassword())) {
            log.info("AuthenticationPassword check pass");
            //生成类似token的对象--也可自定义实现
            // 这是返回的是已经校验通过的Authentication，会设置true，之后使用到这个的对象则不会再次验证
            return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
        } else {
            throw new BadCredentialsException("The username or password is wrong!");
        }
    }

    /**
     * 并且限定Authentication类型是UsernamePasswordAuthenticationToken类型才不用再次验证
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
