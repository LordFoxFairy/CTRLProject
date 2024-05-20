package com.example.authority.config;

import com.example.authority.filter.CaptchaFilter;
import com.example.authority.filter.CustomizeAbstractSecurityInterceptor;
import com.example.authority.filter.JwtAuthenticationFilter;
import com.example.authority.handler.*;
import com.example.authority.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    private final CustomizeAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomizeLogoutSuccessHandler logoutSuccessHandler;
    private final CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;
    private final CustomizeAuthenticationFailureHandler authenticationFailureHandler;
    private final CustomizeSessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    private final CustomizeAbstractSecurityInterceptor securityInterceptor;
    private final CaptchaFilter captchaFilter;
    
    public WebSecurityConfig(CustomizeAuthenticationEntryPoint authenticationEntryPoint,
                             CustomizeLogoutSuccessHandler logoutSuccessHandler,
                             CustomizeAuthenticationSuccessHandler authenticationSuccessHandler,
                             CustomizeAuthenticationFailureHandler authenticationFailureHandler,
                             CustomizeSessionInformationExpiredStrategy sessionInformationExpiredStrategy, CustomizeAbstractSecurityInterceptor securityInterceptor, CaptchaFilter captchaFilter) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.sessionInformationExpiredStrategy = sessionInformationExpiredStrategy;
        this.securityInterceptor = securityInterceptor;
        this.captchaFilter = captchaFilter;
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 设置默认的加密方式（强hash方式加密）
        return new BCryptPasswordEncoder();
    }
    
    
    @Bean
    public UserDetailsService userDetailsService() {
        //获取用户账号密码及权限信息
        return new UserDetailsServiceImpl();
    }
    
    /**
     * configure(AuthenticationManagerBuilder auth) 方法是用来配置Spring Security的身份验证机制的。
     * 在这个方法中，您告诉Spring Security如何从数据库或其他数据源中获取用户详细信息并进行认证。
     * <p>
     * auth.userDetailsService(userDetailsService()) 表示您使用了自定义的UserDetailsService实现来处理身份验证。
     * 这意味着当Spring Security需要验证用户身份时，它将调用您实现的UserDetailsService的实例来获取用户信息。
     *
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置认证方式
        auth.userDetailsService(userDetailsService());
    }

    
    //    @Override
    //    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //        // 配置认证方式等
    //        // 这里可以配置认证方式，例如使用内存、数据库、LDAP等进行认证。
    //        // 以下是一个示例，使用内存认证，用户名为 "user"，密码为 "password"，角色为 "USER"。
    //        // auth.inMemoryAuthentication()
    //        //    .withUser("user")
    //        //    .password("{noop}password")
    //        //    .roles("USER");
    //        super.configure(auth);
    //    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http相关的配置，包括登入登出、异常处理、会话管理等
        // 这里可以配置 HTTP 请求的安全性，例如需要认证的 URL、角色授权等。
        // 以下是一个简单的示例，所有请求都需要进行认证，允许通过基于表单的登录，并配置登录页 URL 和失败 URL。
        // http.authorizeRequests()
        //    .anyRequest().authenticated()
        //    .and()
        //    .formLogin()
        //    .loginPage("/login")
        //    .failureUrl("/login-error");
        //        super.configure(http);
        
        http
                .cors().and().csrf().disable()
                // .authorizeRequests() 方法用于定义访问控制规则，指定哪些URL路径需要特定的权限或角色才能访问。
                .authorizeRequests().antMatchers("/getUser").hasAuthority("query_user").
                
                and().exceptionHandling(). //异常处理(权限拒绝、登录失效等)
                
                authenticationEntryPoint(authenticationEntryPoint). // 匿名用户访问无权限资源时的异常处理
                
                //登入
                and().formLogin().permitAll().//允许所有用户
                successHandler(authenticationSuccessHandler).//登录成功处理逻辑
                failureHandler(authenticationFailureHandler).//登录失败处理逻辑
                
                
                //登出
                and().logout().permitAll().   //允许所有用户
                logoutSuccessHandler(logoutSuccessHandler).     //登出成功处理逻辑
                // 在 Spring Security 中，当用户通过 Spring Security 进行身份验证后，通常会创建一个名为 "JSESSIONID" 的 cookie 来管理用户的会话。
                        deleteCookies("JSESSIONID").     //登出之后删除cookie
        
                // 限制登录用户数量
                and().sessionManagement(). // 配置会话管理
                maximumSessions(1). // 设置同一用户最大并发会话数量
                expiredSessionStrategy(sessionInformationExpiredStrategy); //会话信息过期策略会话信息过期策略(账号被挤下线)
        
        
        // 用于将自定义的安全拦截器 securityInterceptor 添加到 Spring Security 的过滤器链中，在 FilterSecurityInterceptor 之前进行过滤操作。
        http.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class)
                .addFilter(jwtAuthenticationFilter())
                // 验证码过滤器放在UsernamePassword过滤器之前
                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class);
        

    }
    
    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager());
    }
}


