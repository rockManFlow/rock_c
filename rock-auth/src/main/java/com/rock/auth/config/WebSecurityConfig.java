package com.rock.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Slf4j
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity http) throws Exception {

        /**
         * 首先，通过 HttpSecurity 类的 authorizeRequests() 方法对所有访问 HTTP 端点的 HttpServletRequest 进行限制；
         * 然后，anyRequest().authenticated() 语句指定了对于所有请求都需要执行认证，也就是说没有通过认证的用户就无法访问任何端点；
         * 接着，formLogin() 语句用于指定使用表单登录作为认证方式，也就是会弹出一个登录界面；
         * 最后，httpBasic() 语句表示可以使用 HTTP 基础认证（Basic Authentication）方法来完成认证。
         */
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic();

        /**
         *
         * 【1】自定义表单登陆页面
            http.formLogin()
                    .loginPage("/login.html")//自定义登录页面
                    .loginProcessingUrl("/action")//登录表单提交时的处理地址
                    .defaultSuccessUrl("/index");//登录认证成功后的跳转页面

            【2】或者配置自定义用户名和密码
             spring.ecurity.user:
                             name: spring
                             password: spring_password
         */
    }

    /**
     * 使用这个configure来完成：基于AuthenticationManagerBuilder工具类为开发人员提供了基于内存、JDBC、LDAP 等多种验证方案。
     */
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        /**
         * 这是基于内存的用户登陆信息存储

        builder.inMemoryAuthentication()
                .withUser("spring_user").password("password1").roles("USER")
                .and()
                .withUser("spring_admin").password("password2").roles("USER", "ADMIN");
         */
    }

    /**
     * Spring Security 中的用户对象用来描述用户并完成对用户信息的管理，
     * 涉及UserDetails、GrantedAuthority、UserDetailsService 和 UserDetailsManager这四个核心对象。
     *
     * UserDetails：描述 Spring Security 中的用户。
     * GrantedAuthority：定义用户的操作权限。
     * UserDetailsService：定义了对 UserDetails 的查询操作。
     * UserDetailsManager：扩展 UserDetailsService，添加了创建用户、修改用户密码等功能。
     *
     * 定制化用户认证方案
     */
}
