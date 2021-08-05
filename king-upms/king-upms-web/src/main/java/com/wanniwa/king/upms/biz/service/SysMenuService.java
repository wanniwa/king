package com.wanniwa.king.upms.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wanniwa.king.upms.api.entity.SysMenu;

import java.util.List;
import java.util.Set;

public interface SysMenuService extends IService<SysMenu> {

    /**
     * 通过角色id查询菜单权限
     *
     * @param roleId 角色Id
     * @return SysMenu
     */
    List<SysMenu> findMenuByRoleId(Integer roleId);

    /**
     * 通过角色ID查询权限
     *
     * @param roleIds Ids
     * @return List<String>
     */
    Set<String> findPermissionsByRoleIds(String roleIds);
}
