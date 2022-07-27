package com.rock.auth.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 在这个里面进行一些进入校验之前的拦截操作
 *
 * 比如：放行登陆接口，之后返回token信息，其他接口通过校验token是否有效来决定是否允许放行
 * token的校验逻辑就可以放到这个拦截器中进行或者把token校验也可以放到AuthenticationManagerBuilder中来进行
 *
 * filter的作用主要是排除一些无用请求
 *
 * 注意：configure(AuthenticationManagerBuilder auth)配置中并没有配置其他的校验逻辑，
 * 如果这个中还配置了其他的校验逻辑，通过filter的这个还会校验这个，相当于双重验证
 */
@Slf4j
public class MyAuthenticationFilter extends OncePerRequestFilter {
    private AuthenticationManager manager;
    public MyAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.manager=authenticationManager;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //如果认证码为空，说明需要先执行用户名/密码认证
        String username=request.getHeader("username");
        String password=request.getHeader("password");
        String code=request.getHeader("code");

        log.info("MyAuthenticationFilter username:{},password:{},code:{}",username,password,code);
        //两种校验规则
        if (code == null) {
            log.info("MyAuthenticationFilter code null");
            //不存在code，则执行密码校验
            Authentication a = new UsernamePasswordAuthenticationToken(username, password);
            manager.authenticate(a);
        } else {
            log.info("MyAuthenticationFilter code not null");
            //如果认证码不为空，则执行认证码认证
            Authentication a = new AuthCodeAuthenticationToken(username, code);
            manager.authenticate(a);
        }

        log.info("MyAuthenticationFilter chain");
        chain.doFilter(request,response);
    }
}
