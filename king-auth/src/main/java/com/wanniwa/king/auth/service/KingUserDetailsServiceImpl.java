package com.wanniwa.king.auth.service;

import cn.hutool.core.collection.CollectionUtil;
import com.wanniwa.king.admin.api.dto.UserInfo;
import com.wanniwa.king.admin.api.entity.SysUser;
import com.wanniwa.king.admin.api.feign.SysUserClient;
import com.wanniwa.king.common.core.constant.CacheConstants;
import com.wanniwa.king.common.core.utils.R;
import com.wanniwa.king.common.security.constant.SecurityConstants;
import com.wanniwa.king.common.security.service.KingUser;
import com.wanniwa.king.common.security.service.KingUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
public class KingUserDetailsServiceImpl implements KingUserDetailsService {
    private final SysUserClient sysUserClient;
    private final CacheManager cacheManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
        if (cache != null && cache.get(username) != null) {
            return (KingUser) Objects.requireNonNull(cache.get(username)).get();
        }
        R<UserInfo> result = sysUserClient.info(username);
        UserDetails userDetails = getUserDetails(result);
        assert cache != null;
        cache.put(username, userDetails);
        return userDetails;
    }

    private KingUser getUserDetails(R<UserInfo> result) {
        if (result == null || result.getData() == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        UserInfo info = result.getData();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (CollectionUtil.isNotEmpty(info.getRoles())) {
            info.getRoles().forEach(roleId -> grantedAuthorities.add(new SimpleGrantedAuthority(SecurityConstants.ROLE + roleId)));
        }
        if (CollectionUtil.isNotEmpty(info.getPermissions())) {
            info.getPermissions().forEach(permission -> grantedAuthorities.add(new SimpleGrantedAuthority(permission)));
        }
        SysUser user = info.getSysUser();
        // 构造security用户
        return new KingUser(user.getId(), user.getUsername(), SecurityConstants.BCRYPT + user.getPassword(),
                user.getIsEnabled(), true, true, !user.getLockFlag(),
                grantedAuthorities);
    }
}
