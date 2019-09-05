package com.wanniwa.king.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

@Data
@TableName(value = "sys_role")
public class SysRole {
    /**
     * 角色ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 角色标识
     */
    @TableField(value = "code")
    private String code;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 删除状态：0-正常 1-删除
     */
    @TableField(value = "del_flag")
    private Boolean delFlag;

    /**
     * 创建人ID
     */
    @TableField(value = "create_by")
    private Integer createBy;

    /**
     * 修改人ID
     */
    @TableField(value = "update_by")
    private Integer updateBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
}