package com.wanniwa.king.auth.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * WebSecurityConfig 安全控制配置类作为安全控制中心， 用于实现身份认证与授权配置功能
 * EnableWebSecurity 启动 SpringSecurity 过滤器链功能userDetailsService 包含了@Configuration注解
 * WebSecurityConfigurerAdapter @Order(100)
 *
 * @author wanniwa
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 遇到新密码,DelegatingPasswordEncoder会委托给BCryptPasswordEncoder(encodingId为bcryp*)进行加密,
     * 同时,对历史上使用ldap,MD4,MD5等等加密算法的密码认证保持兼容(如果数据库里的密码使用的是MD5算法,那使用matches方法认证仍可以通过,但新密码会使bcrypt进行储存)
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    //@Override
    //protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //    //auth.inMemoryAuthentication()
    //    //        .withUser("irving")
    //    //        .password(passwordEncoder().encode("123456"))
    //    //        .roles("read");
    //     auth.userDetailsService(kingUserDetailsService);
    //    //   .passwordEncoder(passwordEncoder());
    //}


    /**
     * 资源权限配置（过滤器链）:
     * 1、被拦截的资源
     * 2、资源所对应的角色权限
     * 3、定义认证方式：httpBasic 、httpForm
     * 4、定制登录页面、登录请求地址、错误处理方式
     * 5、自定义 spring security 过滤器
     *
     * @param http HttpSecurity
     */
    @Override
    @SneakyThrows
    protected void configure(HttpSecurity http) {
        http
                .formLogin()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    /**
     * password 密码模式要使用此认证管理器
     *
     * @return AuthenticationManager
     */
    @Bean
    @Override
    @SneakyThrows
    public AuthenticationManager authenticationManagerBean() {
        return super.authenticationManagerBean();
    }
}
