package com.wanniwa.king.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "sys_user")
public class SysUser {
    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 盐
     */
    @TableField(value = "salt")
    private String salt;

    /**
     * 简介
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 部门ID
     */
    @TableField(value = "dept_id")
    private Integer deptId;

    /**
     * 0-未启用，1-启用
     */
    @TableField(value = "is_enabled")
    private Boolean isEnabled;

    /**
     * 0 未锁定 1 锁定
     */
    @TableField(value = "lock_flag")
    private Boolean lockFlag;

    /**
     * 删除状态：0-正常 1-删除
     */
    @TableField(value = "del_flag")
    private Boolean delFlag;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
}
