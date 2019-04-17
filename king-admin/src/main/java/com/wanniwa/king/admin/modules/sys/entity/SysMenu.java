package com.wanniwa.king.admin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

@Data
@TableName(value = "sys_menu")
public class SysMenu {
    /**
     * 菜单ID
     */
     @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    /**
     * 菜单名称
     */
    @TableField(value = "menu_name")
    private String menuName;

    /**
     * 父级编号
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 所有父级编号
     */
    @TableField(value = "parent_ids")
    private String parentIds;

    /**
     * 排序
     */
    @TableField(value = "order_num")
    private Integer orderNum;

    /**
     * url
     */
    @TableField(value = "url")
    private String url;

    /**
     * 权限标识
     */
    @TableField(value = "perms")
    private String perms;

    /**
     * 类型
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 状态1:正常,2:冻结
     */
    @TableField(value = "state")
    private String state;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 创建人id
     */
    @TableField(value = "create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 修改人id
     */
    @TableField(value = "update_by")
    private Long updateBy;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    public static final String COL_MENU_NAME = "menu_name";

    public static final String COL_PARENT_ID = "parent_id";

    public static final String COL_PARENT_IDS = "parent_ids";

    public static final String COL_ORDER_NUM = "order_num";

    public static final String COL_URL = "url";

    public static final String COL_PERMS = "perms";

    public static final String COL_TYPE = "type";

    public static final String COL_STATE = "state";

    public static final String COL_ICON = "icon";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_UPDATE_TIME = "update_time";
}