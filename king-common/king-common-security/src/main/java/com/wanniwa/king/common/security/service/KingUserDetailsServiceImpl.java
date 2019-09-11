package com.wanniwa.king.common.security.service;

import cn.hutool.system.UserInfo;
import com.wanniwa.king.admin.entity.SysUser;
import com.wanniwa.king.admin.feign.ISysUserClient;
import com.wanniwa.king.common.core.constant.CacheConstants;

import com.wanniwa.king.common.core.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
@AllArgsConstructor
public class KingUserDetailsServiceImpl implements KingUserDetailsService {
    private final ISysUserClient sysUserClient;
    private final CacheManager cacheManager;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
        if (cache != null && cache.get(username) != null) {
            return (KingUser) cache.get(username).get();
        }

        R<SysUser> result = sysUserClient.info(username);
        UserDetails userDetails = getUserDetails(result);
        assert cache != null;
        cache.put(username, userDetails);
        return userDetails;
    }

   private UserDetails getUserDetails(R<SysUser> result) {

   }
}
