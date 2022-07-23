package com.rock.auth.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

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
public class MyAuthenticationFilter extends BasicAuthenticationFilter {
    public MyAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


}
