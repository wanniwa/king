package com.wanniwa.king.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "sys_menu")
public class SysMenu {
    /**
     * 菜单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 请求地址
     */
    @TableField(value = "path")
    private String path;

    /**
     * 父级编号
     */
    @TableField(value = "parent_id")
    private Integer parentId;

    /**
     * 所有父级编号
     */
    @TableField(value = "parent_ids")
    private String parentIds;

    /**
     * 角色标识
     */
    @TableField(value = "permission")
    private String permission;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 菜单类型：0-菜单 1-按钮
     */
    @TableField(value = "type")
    private Byte type;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

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