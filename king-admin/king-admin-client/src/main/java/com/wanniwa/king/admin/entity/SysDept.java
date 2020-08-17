package com.wanniwa.king.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

@Data
@TableName(value = "sys_dept")
public class SysDept {
    /**
     * 部门ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 部门名称
     */
    @TableField(value = "name")
    private String name;

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
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

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
