package com.wanniwa.king.common.security.config;

import com.wanniwa.king.common.security.component.ResourceAuthExceptionEntryPoint;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/*
 * 提供 user 信息，所以 oauth2-server 也是一个Resource Server
 * */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Autowired
    TokenStore tokenStore;

    @Override
    @SneakyThrows
    public void configure(HttpSecurity http) {
        http
                .authorizeRequests()
                .antMatchers("/api/user/info").permitAll()
                //.anyRequest().authenticated()
                .and()
                .csrf().disable();
    }
    //    @Override
    //    public void configure(HttpSecurity http) throws Exception {
    //        http
    //                // Since we want the protected resources to be accessible in the UI as well we need
    //                // session creation to be allowed (it's disabled by default in 2.0.6)
    //                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
    //                .and()
    //                .requestMatchers().anyRequest()
    //                .and()
    //                .anonymous()
    //                .and()
    //                .authorizeRequests()
    ////              .antMatchers("/product/**").access("#oauth2.hasScope('select') and hasRole('ROLE_USER')")
    //                .antMatchers("/user/**").authenticated();//必须认证过后才可以访问
    //    }


    //    @Override
    //    public void configure(HttpSecurity http) throws Exception {
    //        http.requestMatchers().anyRequest()
    //                .and()
    //                .authorizeRequests()
    //                .antMatchers("/api/**").authenticated();
    //    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resource) throws Exception {

        //这里把自定义异常加进去
        resource.tokenStore(tokenStore).authenticationEntryPoint(new ResourceAuthExceptionEntryPoint());
    }
}