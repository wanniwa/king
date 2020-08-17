package com.wanniwa.king.admin.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanniwa.king.admin.dto.UserInfo;
import com.wanniwa.king.admin.entity.SysRole;
import com.wanniwa.king.admin.entity.SysUser;
import com.wanniwa.king.admin.mapper.SysUserMapper;
import com.wanniwa.king.admin.service.SysMenuService;
import com.wanniwa.king.admin.service.SysRoleService;
import com.wanniwa.king.admin.service.SysUserService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    private final SysRoleService sysRoleService;
    private final SysMenuService sysMenuService;

    /**
     * 将查询到的SysUser信息、角色信息、权限、补全到UserInfo中
     *
     * @param sysUser
     * @return
     */
    @Override
    public UserInfo getUserInfo(SysUser sysUser) {
        UserInfo userInfo = new UserInfo();
        userInfo.setSysUser(sysUser);
        //角色
        Set<Long> roleIdList = sysRoleService.findRolesByUserId(sysUser.getId())
                .stream()
                .map(SysRole::getId)
                .collect(Collectors.toSet());
        userInfo.setRoles(roleIdList);
        //权限
        Set<String> permissions = sysMenuService.findPermissionsByRoleIds(StringUtils.join(roleIdList, ","));
        userInfo.setPermissions(permissions);
        return userInfo;
    }
}
















