package com.wanniwa.king.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanniwa.king.admin.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 通过角色编号查询菜单
     *
     * @param roleId 角色ID
     * @return List<SysMenu>
     */
    List<SysMenu> findMenuByRoleId(@Param("roleId") Integer roleId);
    /**
     * 通过角色ID查询权限
     *
     * @param roleIds Ids
     * @return List<String>
     */
    List<String> findPermissionsByRoleIds(String roleIds);
}