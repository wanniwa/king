package com.wanniwa.king.admin.config;

//import com.wanniwa.king.common.security.component.ResourceAuthExceptionEntryPoint;
//import lombok.SneakyThrows;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 提供 user 信息，所以 oauth2-server 也是一个Resource Server
 *
 * @author wanniwa
 */
//@Configuration
//@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//
//    @Override
//    @SneakyThrows
//    public void configure(HttpSecurity http) {
//        http
//                .authorizeRequests()
//                .antMatchers("/api/user/**").permitAll()
//                // 资源授权规则
//                .antMatchers("/product/**").hasAuthority("product")
//                // 所有的请求对应访问的用户都要有 all 范围权限
//                .antMatchers("/**").access("#oauth2.hasScope('all')")
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable();
//    }
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resource) throws Exception {
//        //这里把自定义异常加进去
//        resource.authenticationEntryPoint(new ResourceAuthExceptionEntryPoint());
//    }
//}
