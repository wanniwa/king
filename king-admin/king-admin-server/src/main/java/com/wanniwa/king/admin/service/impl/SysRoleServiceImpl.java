package com.wanniwa.king.admin.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanniwa.king.admin.entity.SysRole;
import com.wanniwa.king.admin.mapper.SysRoleMapper;
import com.wanniwa.king.admin.service.SysRoleService;

import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public List<SysRole> findRolesByUserId(Integer userId) {
        return baseMapper.selectListByUserId(userId);
    }
}









