package com.wanniwa.king.admin.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanniwa.king.admin.mapper.SysMenuMapper;
import com.wanniwa.king.admin.entity.SysMenu;
import com.wanniwa.king.admin.service.SysMenuService;

import java.util.List;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Override
    public List<SysMenu> findMenuByRoleId(Integer roleId) {
        return baseMapper.findMenuByRoleId(roleId);
    }

    @Override
    public List<String> findPermissionsByRoleIds(String roleIds) {
        return baseMapper.findPermissionsByRoleIds(roleIds);
    }
}





