package com.wanniwa.king.common.enums;

import lombok.Getter;

/**
 * @author wanniwa
 */
@Getter
public enum RoleEnum {
    /**
     * 买家
     */
    BUYER(1,"买家"),
    /**
     * 卖家
     */
    SELLER(2,"卖家");
    private int code ;
    private String msg;
    RoleEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
