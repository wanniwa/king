package com.wanniwa.king.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "user_info")
public class UserInfo {
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    /**
     * 微信openid
     */
    @TableField(value = "openid")
    private String openid;

    /**
     * 1买家2卖家
     */
    @TableField(value = "role")
    private Integer role;

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

    public static final String COL_USERNAME = "username";

    public static final String COL_PASSWORD = "password";

    public static final String COL_OPENID = "openid";

    public static final String COL_ROLE = "role";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}