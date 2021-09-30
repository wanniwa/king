package com.wanniwa.king.upms.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanniwa.king.upms.api.entity.SysMenu;
import com.wanniwa.king.upms.biz.mapper.SysMenuMapper;
import com.wanniwa.king.upms.biz.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Override
    public List<SysMenu> findMenuByRoleId(Integer roleId) {
        return baseMapper.findMenuByRoleId(roleId);
    }

    @Override
    public Set<String> findPermissionsByRoleIds(String roleIds) {
        return baseMapper.findPermissionsByRoleIds(roleIds);
    }
}






