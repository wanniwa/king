package com.wanniwa.king.admin.dto;

import com.wanniwa.king.admin.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@Data
@ApiModel(value = "用户信息")
public class UserInfo {
    /**
     * 用户基本信息
     */
    @ApiModelProperty(value = "用户基本信息")
    private SysUser sysUser;
    /**
     * 权限标识集合
     */
    @ApiModelProperty(value = "权限标识集合")
    private Set<String> permissions;
    /**
     * 角色集合
     */
    @ApiModelProperty(value = "角色标识集合")
    private Set<Long> roles;
}
