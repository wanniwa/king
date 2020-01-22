package com.wanniwa.king.common.security.service;

import com.wanniwa.king.common.core.constant.CacheConstants;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * 用来配置客户端详情服务
 *
 * @author wanniwa
 */
public class KingClientDetailsService extends JdbcClientDetailsService {


    public KingClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 重写原生方法支持redis缓存
     *
     * @param clientId The client id
     * @return Client details for OAuth 2
     * @throws InvalidClientException Exception thrown when a client was unable to authenticate.
     */
    @Override
    @Cacheable(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
    public ClientDetails loadClientByClientId(String clientId) {
        return super.loadClientByClientId(clientId);
    }
}
