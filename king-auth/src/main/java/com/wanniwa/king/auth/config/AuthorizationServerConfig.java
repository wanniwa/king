package com.wanniwa.king.auth.config;

import com.wanniwa.king.common.core.constant.SecurityConstants;
import com.wanniwa.king.common.security.provider.error.KingWebResponseExceptionTranslator;
import com.wanniwa.king.common.security.service.KingClientDetailsService;
import com.wanniwa.king.common.security.service.KingUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
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
 * 认证服务器配置
 * <p>
 * 授权服务配置总结：授权服务配置分成三大块，可以关联记忆。
 * 既然要完成认证，它首先得知道客户端信息从哪儿读取，因此要进行客户端详情配置。
 * 既然要颁发token，那必须得定义token的相关endpoint，以及token如何存取，以及客户端支持哪些类型的token。
 * 既然暴露除了一些endpoint，那对这些endpoint可以定义一些安全上的约束等。
 * <p>
 * EnableAuthorizationServer 开启认证服务器功能
 * <p>
 * ClientDetailsServiceConfigurer：用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
 * AuthorizationServerSecurityConfigurer：用来配置令牌端点(Token Endpoint)的安全约束.
 * AuthorizationServerEndpointsConfigurer：用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
 *
 * @author wanniwa
 */
@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final DataSource dataSource;
    private final RedisConnectionFactory redisConnectionFactory;
    private final KingUserDetailsService kingUserDetailsService;


    /**
     * AuthorizationServerSecurityConfigurer：
     * 用来配置令牌端点(Token Endpoint)的安全与权限访问。
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {

        //
        security.checkTokenAccess("isAuthenticated()");

        // 开启/oauth/token_key验证端口无权限访问
        // url:/oauth/token_key,exposes public key for token verification if using JWT tokens
        security.tokenKeyAccess("permitAll()");

        security.allowFormAuthenticationForClients();

    }

    /**
     * ClientDetailsServiceConfigurer
     * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在
     * 这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     * 一般使用数据库来存储或读取应用配置的详情信息（client_id ，client_secret，redirect_uri 等配置信息）。
     *
     * @param clients client服务配置
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //从数据库读取client信息，并缓存到redis中
        KingClientDetailsService kingClientDetailsService = new KingClientDetailsService(dataSource);
        clients.withClientDetails(kingClientDetailsService);
    }

    /**
     * AuthorizationServerEndpointsConfigurer
     * 用来配置授权以及令牌（Token）的访问端点和令牌服务（比如：配置令牌的签名与存储方式）
     *
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
                .userDetailsService(kingUserDetailsService)
                .reuseRefreshTokens(false)
                .exceptionTranslator(webResponseExceptionTranslator());
    }

    @Bean
    public WebResponseExceptionTranslator<OAuth2Exception> webResponseExceptionTranslator() {
        return new KingWebResponseExceptionTranslator();
    }

    /**
     * token存储
     *
     * @return TokenStore
     */
    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix(SecurityConstants.KING_PREFIX + SecurityConstants.OAUTH_PREFIX);
        return tokenStore;
    }

}
