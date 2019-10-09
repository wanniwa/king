package com.wanniwa.king.common.security.service;

import cn.hutool.core.util.ArrayUtil;
import com.wanniwa.king.admin.dto.UserInfo;
import com.wanniwa.king.admin.entity.SysUser;
import com.wanniwa.king.admin.feign.ISysUserClient;
import com.wanniwa.king.common.core.constant.CacheConstants;
import com.wanniwa.king.common.core.constant.SecurityConstants;
import com.wanniwa.king.common.core.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Service
@AllArgsConstructor
public class KingUserDetailsServiceImpl implements KingUserDetailsService {
    private final ISysUserClient sysUserClient;
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
        Set<GrantedAuthority> grantedAuthorities =  new HashSet<>();
        if (ArrayUtil.isNotEmpty(info.getRoles())) {
            // 获取角色
            Arrays.stream(info.getRoles()).forEach(roleId -> grantedAuthorities.add(new SimpleGrantedAuthority(SecurityConstants.ROLE + roleId)));
            // 获取资源
        }
        if (ArrayUtil.isNotEmpty(info.getPermissions())) {
            Arrays.stream(info.getPermissions()).forEach( permission-> grantedAuthorities.add(new SimpleGrantedAuthority(permission)));
        }
        SysUser user = info.getSysUser();
        // 构造security用户
        return new KingUser(user.getUsername(), user.getPassword(),
                user.getEnabled(), true, true, !user.getLockFlag(),
                grantedAuthorities, user.getId(), user.getDeptId());
    }
}
