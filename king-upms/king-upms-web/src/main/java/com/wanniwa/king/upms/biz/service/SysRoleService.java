package com.wanniwa.king.upms.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wanniwa.king.upms.api.entity.SysRole;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {

    /**
     * 通过用户查询拥有角色
     *
     * @param userId 用户id
     * @return 角色列表
     */
    List<SysRole> findRolesByUserId(Long userId);
}










