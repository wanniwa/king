package com.wanniwa.king.auth.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wanniwa.king.auth.entity.UserInfo;

public interface UserInfoService extends IService<UserInfo> {
    /**
     * 根据openId查询用户
     * @param openId
     * @return
     */
    UserInfo findByOpenId(String openId);

}
