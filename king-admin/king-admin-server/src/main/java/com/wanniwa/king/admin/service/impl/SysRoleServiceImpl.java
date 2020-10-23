package com.wanniwa.king.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanniwa.king.admin.api.entity.SysRole;
import com.wanniwa.king.admin.mapper.SysRoleMapper;
import com.wanniwa.king.admin.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public List<SysRole> findRolesByUserId(Long userId) {
        return baseMapper.selectListByUserId(userId);
    }
}










