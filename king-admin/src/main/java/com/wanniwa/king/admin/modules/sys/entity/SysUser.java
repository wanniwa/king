package com.wanniwa.king.admin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

@ApiModel(value = "用户表")
@Data
@TableName(value = "sys_user")
public class SysUser {
    /**
     * 用户id
     */
    @TableId(value = "user_id" , type = IdType.AUTO)
    @ApiModelProperty(value = "用户id",example = "1")
    private Long userId;

    /**
     * 用户名
     */
    @TableField(value = "username")
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 密码盐
     */
    @TableField(value = "salt")
    @ApiModelProperty(value = "密码盐")
    private String salt;

    /**
     * 电话
     */
    @TableField(value = "phone")
    @ApiModelProperty(value = "电话")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 删除状态
     */
    @TableField(value = "is_delete")
    @ApiModelProperty(value = "删除状态")
    private Boolean isDelete;

    /**
     * 状态0 停用 1 启用
     */
    @TableField(value = "state")
    @ApiModelProperty(value = "状态0 停用 1 启用",example = "1")
    private Integer state;

    /**
     * 创建人id
     */
    @TableField(value = "create_by")
    @ApiModelProperty(value = "创建人id",example = "1")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改人id
     */
    @TableField(value = "update_by")
    @ApiModelProperty(value = "修改人id",example = "1")
    private Long updateBy;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;


}