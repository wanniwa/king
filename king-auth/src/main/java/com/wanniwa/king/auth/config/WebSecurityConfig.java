package com.wanniwa.king.auth.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@Configuration
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

    @Override
    @SneakyThrows
    protected void configure(HttpSecurity http) {
        http
                .authorizeRequests()
                .antMatchers("/oauth/**","/actuator/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();

    }
    /**
     * 必须注入 AuthenticationManager，不然oauth  无法处理四种授权方式
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
