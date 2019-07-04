package com.wanniwa.king.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

@ApiModel(value="com.wanniwa.king.auth.entity.UserInfo")
@Data
@TableName(value = "user_info")
public class UserInfo {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="null")
    private String id;

    @TableField(value = "username")
    @ApiModelProperty(value="null")
    private String username;

    @TableField(value = "password")
    @ApiModelProperty(value="null")
    private String password;

    /**
     * 微信openid
     */
    @TableField(value = "openid")
    @ApiModelProperty(value="微信openid")
    private String openid;

    /**
     * 1买家2卖家
     */
    @TableField(value = "role")
    @ApiModelProperty(value="1买家2卖家")
    private Integer role;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="修改时间")
    private Date updateTime;

    public static final String COL_USERNAME = "username";

    public static final String COL_PASSWORD = "password";

    public static final String COL_OPENID = "openid";

    public static final String COL_ROLE = "role";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}