package com.wanniwa.king.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wanniwa.king.admin.api.dto.UserInfo;
import com.wanniwa.king.admin.api.entity.SysUser;

/**
 * 用户
 *
 * @author wanniwa
 * @date 2019/12/30
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 通过查用户的全部信息
     *
     * @param sysUser 用户
     * @return 用户的全部信息
     */
    UserInfo getUserInfo(SysUser sysUser);
}











