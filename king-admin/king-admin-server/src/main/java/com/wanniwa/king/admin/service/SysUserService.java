package com.wanniwa.king.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wanniwa.king.admin.dto.UserInfo;
import com.wanniwa.king.admin.entity.SysUser;

public interface SysUserService extends IService<SysUser> {

    /**
     * 查询用户信息
     *
     * @param username
     * @return
     */
    UserInfo getUserInfoByUsername(String username);
}








