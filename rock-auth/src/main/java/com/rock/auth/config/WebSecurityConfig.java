package com.rock.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Slf4j
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * todo 这个配置中完成角色和权限的控制配置
     * @param http
     */
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
         * anonymous()	允许匿名访问
         * authenticated()	允许认证用户访问
         * denyAll()	无条件禁止一切访问
         * hasAnyAuthority(String)	允许具有任一权限的用户进行访问
         * hasAnyRole(String)	允许具有任一角色的用户进行访问
         * hasAuthority(String)	允许具有特定权限的用户进行访问
         * hasIpAddress(String)	允许来自特定 IP 地址的用户进行访问
         * hasRole(String)	允许具有特定角色的用户进行访问
         * permitAll()	无条件允许一切访问
         */

        //放行一些路径
//        String[] permitsURL=new String[10];
//        for (String permit : permitsURL) {
        //ant匹配器
//            http.authorizeRequests().antMatchers(new String[]{permit}).permitAll();
//        }


        //可以添加一些拦截器，在访问之前进行一些拦截操作

        /**
         * 过滤器放置在过滤器链的具体位置需要符合每个过滤器本身的功能特性，不能将这些过滤器随意排列组合---BasicAuthenticationFilter内置的一个过滤器
         * addFilterBefore() 和 addFilterAfter() 方法在
         * BasicAuthenticationFilter 之前和之后分别添加了 RequestValidationFilter 和 LoggingFilter。
         */
//        http.addFilterBefore(new RequestValidationFilter(),BasicAuthenticationFilter.class)
//                .addFilterAfter(new LoggingFilter(),BasicAuthenticationFilter.class)
//                .authorizeRequests()
//                .anyRequest()
//                .permitAll();


        /**
         * 路径hello_user仅允许角色是USER的访问，hello_admin仅允许ADMIN访问
         * 其他的需要进行校验访问
         */
//        http.authorizeRequests()
//                //mvc匹配器
//                .mvcMatchers("/hello_user").hasRole("USER")
//                .mvcMatchers("/hello_admin").hasRole("ADMIN")
//                .anyRequest().authenticated();

        //授权
//        http.authorizeRequests().anyRequest().hasAnyAuthority("quanxian1");

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


    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    /**
     * 使用这个configure来完成：基于AuthenticationManagerBuilder工具类为开发人员提供了基于内存、JDBC、LDAP 等多种验证方案。
     *
     * todo 该配置方法中完成自定义登陆密码的校验等逻辑
     */
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        /**
         * AuthenticationManagerBuilder 将基于这个自定义的 SpringUserDetailsService 完成 UserDetails 的创建和管理，
         * 并基于自定义的 SpringAuthenticationProvider 完成用户认证。
         */
        builder.userDetailsService(userDetailsService).and().authenticationProvider(authenticationProvider);
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
