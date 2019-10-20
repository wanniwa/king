package com.wanniwa.king.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanniwa.king.admin.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 通过用户查询拥有角色
     * @param userId 用户id
     * @return 角色列表
     */
    List<SysRole> selectListByUserId(@Param("userId") Integer userId);
}