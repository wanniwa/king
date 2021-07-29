//package com.wanniwa.king.auth.service;
//
//import com.wanniwa.king.common.core.constant.CacheConstants;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.security.oauth2.provider.ClientDetails;
//import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
//
//import javax.sql.DataSource;
//
///**
// * 用来配置客户端详情服务
// *
// * @author wanniwa
// */
//public class KingClientDetailsServiceImpl extends JdbcClientDetailsService {
//
//
//    public KingClientDetailsServiceImpl(DataSource dataSource) {
//        super(dataSource);
//    }
//
//    /**
//     * 重写原生方法支持redis缓存
//     *
//     * @param clientId The client id
//     * @return Client details for OAuth 2
//     */
//    @Override
//    @Cacheable(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
//    public ClientDetails loadClientByClientId(String clientId) {
//        return super.loadClientByClientId(clientId);
//    }
//}
