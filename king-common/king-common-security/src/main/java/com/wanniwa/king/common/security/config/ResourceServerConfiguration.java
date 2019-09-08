package com.wanniwa.king.common.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/*
* 提供 user 信息，所以 oauth2-server 也是一个Resource Server
* */
@Configuration
@EnableResourceServer
//@Order(3)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    //@Override
    //@SneakyThrows
    //public void configure(HttpSecurity http) {
    //    http.requestMatchers()
    //            .anyRequest()
    //            .and()
    //            .authorizeRequests()
    //            .anyRequest().permitAll()
    //            .antMatchers("/oauth/**").permitAll();
    //}
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
}