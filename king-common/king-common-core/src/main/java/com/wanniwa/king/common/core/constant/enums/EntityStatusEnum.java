package com.wanniwa.king.common.core.constant.enums;

import com.wanniwa.king.common.core.utils.ICodeMsg;
import lombok.Getter;

/**
 * 数据库字段状态枚举
 * @author wanniwa
 */
@Getter
public enum EntityStatusEnum implements ICodeMsg {

    /**
     * 状态：正常
     */
    NORMAL(0, "正常"),
    /**
     * 状态：启用
     */
    ENABLED(1, "启用"),
    /**
     * 状态：锁定
     */
    LOCKED(2, "锁定"),
    /**
     * 状态：停用
     */
    DISABLED(3, "停用");


    private int code;

    private String msg;

    EntityStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

