package com.wanniwa.king.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "sys_user_role")
public class SysUserRole {
    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    private Long roleId;
}
