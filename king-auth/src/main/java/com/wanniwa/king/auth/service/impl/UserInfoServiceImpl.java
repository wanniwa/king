package com.wanniwa.king.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanniwa.king.auth.mapper.UserInfoMapper;
import com.wanniwa.king.auth.entity.UserInfo;
import com.wanniwa.king.auth.service.UserInfoService;
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService{

    @Override
    public UserInfo findByOpenId(String openId) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getOpenid, openId);
        return this.getOne(queryWrapper);
    }
}
