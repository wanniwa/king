package com.wanniwa.king.common.security.service;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class KingUser extends User {

    /**
     * 用户ID
     */
    @Getter
    private Integer id;
    /**
     * 部门ID
     */
    @Getter
    private Integer deptId;

    public KingUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Integer id, Integer deptId) {
        super(username, password, authorities);
        this.id = id;
        this.deptId = deptId;
    }

    public KingUser(String username, String password, boolean isEnabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Integer id, Integer deptId) {
        super(username, password, isEnabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.deptId = deptId;
    }
}
