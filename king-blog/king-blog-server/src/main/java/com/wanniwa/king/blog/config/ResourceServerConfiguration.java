//package com.wanniwa.king.blog.config;
//
//import com.wanniwa.king.common.security.component.ResourceAuthExceptionEntryPoint;
//import lombok.SneakyThrows;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//
///**
// * 提供 user 信息，所以 oauth2-server 也是一个Resource Server
// *
// * @author wanniwa
// */
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//
//    @Override
//    @SneakyThrows
//    public void configure(HttpSecurity http) {
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable();
//    }
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resource) {
//        //这里把自定义异常加进去
//        resource.authenticationEntryPoint(new ResourceAuthExceptionEntryPoint());
//    }
//}
