package com.wanniwa.king.upms.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanniwa.king.upms.api.entity.SysRole;
import com.wanniwa.king.upms.biz.mapper.SysRoleMapper;
import com.wanniwa.king.upms.biz.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public List<SysRole> findRolesByUserId(Long userId) {
        return baseMapper.selectListByUserId(userId);
    }
}










