package com.wanniwa.king.admin.service;

import com.wanniwa.king.admin.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Arrays;
import java.util.List;

public interface SysRoleService extends IService<SysRole> {

    /**
     * 通过用户查询拥有角色
     * @param userId 用户id
     * @return 角色列表
     */
    List<SysRole> findRolesByUserId(Integer userId);
}









