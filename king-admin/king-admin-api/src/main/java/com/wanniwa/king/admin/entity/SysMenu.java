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
     * 菜单权限标识
     */
    @TableField(value = "permission")
    private String permission;

    /**
     * 前端URL
     */
    @TableField(value = "path")
    private String path;

    /**
     * 父菜单ID
     */
    @TableField(value = "parent_id")
    private Integer parentId;

    /**
     * 所有父级编号
     */
    @TableField(value = "parent_ids")
    private String parentIds;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * VUE页面
     */
    @TableField(value = "component")
    private String component;

    /**
     * 排序值
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 0-开启，1- 关闭
     */
    @TableField(value = "keep_alive")
    private String keepAlive;

    /**
     * 菜单类型：0-目录 1-菜单 2-按钮
     */
    @TableField(value = "type")
    private Byte type;

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