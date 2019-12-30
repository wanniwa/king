package com.wanniwa.king.common.security.util;

import cn.hutool.core.util.StrUtil;
import com.wanniwa.king.common.core.constant.SecurityConstants;
import com.wanniwa.king.common.exception.ResultException;
import com.wanniwa.king.common.security.service.KingUser;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 获取登录用户信息工具
 *
 * @author wanniwa
 * @date 2019/12/30
 */
@UtilityClass
public class SecurityUtil {
    /**
     * 获取Authentication
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     */
    public KingUser getUser() {
        try {
            return (KingUser) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new ResultException(HttpStatus.UNAUTHORIZED.value(), "登录状态过期");
        }
    }

    /**
     * 获取用户角色信息
     *
     * @return 角色集合
     */
    public List<Integer> getRoles() {
        Authentication authentication = getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<Integer> roleIds = new ArrayList<>();
        authorities.stream()
                .filter(granted -> StrUtil.startWith(granted.getAuthority(), SecurityConstants.ROLE))
                .forEach(granted -> {
                    String id = StrUtil.removePrefix(granted.getAuthority(), SecurityConstants.ROLE);
                    roleIds.add(Integer.parseInt(id));
                });
        return roleIds;
    }
}
