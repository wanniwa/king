/*
 *
 *      Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the pig4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: lengleng (wangiegie@gmail.com)
 *
 */

package com.wanniwa.king.auth.config;

import com.wanniwa.king.common.security.provider.error.KingWebResponseExceptionTranslator;
import com.wanniwa.king.common.security.service.KingClientDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * @author wanniwa
 * 认证服务器配置
 * ClientDetailsServiceConfigurer：用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
 * AuthorizationServerSecurityConfigurer：用来配置令牌端点(Token Endpoint)的安全约束.
 * AuthorizationServerEndpointsConfigurer：用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
 */
@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private final DataSource dataSource;
    private final RedisConnectionFactory redisConnectionFactory;
    /**
     * AuthorizationServerSecurityConfigurer：用来配置令牌端点(Token Endpoint)的安全与权限访问。
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                //开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")         //url:/oauth/token_key,exposes public key for token verification if using JWT tokens
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();

    }

    /**
     * ClientDetailsServiceConfigurer：用来配置客户端详情信息，一般使用数据库来存储或读取应用配置的详情信息（client_id ，client_secret，redirect_uri 等配置信息）。
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //从数据库读取client信息，并缓存到redis中
        clients.withClientDetails(new KingClientDetailsService(dataSource));
                //// clients.inMemory()
                //.withClient("client_1")
                //.authorizedGrantTypes("client_credentials")
                //.scopes("all","read", "write")
                //.authorities("client_credentials")
                //.accessTokenValiditySeconds(7200)
                //.secret(passwordEncoder.encode("123456"))
                //
                //.and().withClient("client_2")
                //.authorizedGrantTypes("password", "refresh_token")
                //.scopes("all","read", "write")
                //.accessTokenValiditySeconds(7200)
                //.refreshTokenValiditySeconds(10000)
                //.authorities("password")
                //.secret(passwordEncoder.encode("123456"))
                //
                //.and().withClient("client_3").authorities("authorization_code","refresh_token")
                //.secret(passwordEncoder.encode("123456"))
                //.authorizedGrantTypes("authorization_code")
                //.scopes("all","read", "write")
                //.accessTokenValiditySeconds(7200)
                //.refreshTokenValiditySeconds(10000)
                //.redirectUris("http://localhost:8080/callback","http://localhost:8080/signin")
                //
                //.and().withClient("client_test")
                //.secret(passwordEncoder.encode("123456"))
                //.authorizedGrantTypes("all flow")
                //.authorizedGrantTypes("authorization_code", "client_credentials", "refresh_token","password", "implicit")
                //.redirectUris("http://localhost:8080/callback","http://localhost:8080/signin")
                //.scopes("all","read", "write")
                //.accessTokenValiditySeconds(7200)
                //.refreshTokenValiditySeconds(10000);
        System.out.println("secret:"+passwordEncoder.encode("123456"));
        //super.configure(clients);
    }

    /**
     * AuthorizationServerEndpointsConfigurer：用来配置授权以及令牌（Token）的访问端点和令牌服务（比如：配置令牌的签名与存储方式）
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(tokenStore())
                //.tokenEnhancer(tokenEnhancer())
                //.userDetailsService(pigxUserDetailsService)
                //.authenticationManager(authenticationManagerBean)
                //.reuseRefreshTokens(false)
                //.pathMapping("/oauth/confirm_access", "/token/confirm_access")
                .exceptionTranslator(webResponseExceptionTranslator());
    }
    @Bean
    public WebResponseExceptionTranslator<OAuth2Exception> webResponseExceptionTranslator(){
        return new KingWebResponseExceptionTranslator();
    }

    /**
     *  token存储
     * @return TokenStore;
     */
    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        return tokenStore;
    }

}