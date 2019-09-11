package com.wanniwa.king.common.security.service;

import cn.hutool.system.UserInfo;
import com.wanniwa.king.common.core.constant.CacheConstants;
import com.wanniwa.king.common.core.constant.SecurityConstants;
import com.wanniwa.king.common.core.utils.R;
import com.wanniwa.king.common.security.service.KingUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
@AllArgsConstructor
public class KingUserDetailsServiceImpl implements UserDetailsService {
    private final RemoteUserService remoteUserService;
    private final CacheManager cacheManager;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
        if (cache != null && cache.get(username) != null) {
            return (PigxUser) cache.get(username).get();
        }

        R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
        UserDetails userDetails = getUserDetails(result);
        cache.put(username, userDetails);
        return userDetails;
        return null;
    }
}
