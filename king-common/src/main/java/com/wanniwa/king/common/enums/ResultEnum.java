package com.wanniwa.king.common.enums;

import com.wanniwa.king.common.utils.ICodeMsg;

/**
 *
 * @author wanniwa
 */

public enum ResultEnum implements ICodeMsg {
    /**
     * 操作失败
     */
    FAILED(-1, "操作失败"),
    /**
     * 成功
     */
    SUCCESS(0, "成功"),
    /**
     * 参数错误
     */
    PARAM_ERROR(1, "参数错误"),
    /**
     * 登录失败
     */
    LOGIN_ERROR(2,"登录失败"),
    /**
     * 登录失败
     */
    ROLE_ERROR(3,"角色权限有误"),
    /**
     * 购物车为空
     */
    CART_EMPTY(1000, "购物车为空");

    private final int code;
    private final String msg;

    ResultEnum(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultEnum fromCode(int code) {
        ResultEnum[] ecs = ResultEnum.values();
        for (ResultEnum ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return SUCCESS;
    }

    public static String getMsgByCode(Integer code) {
        ResultEnum[] array = ResultEnum.values();
        for (ResultEnum obj : array) {
            if (code.equals(obj.code)) {
                return obj.msg;
            }
        }
        return "";
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return String.format(" ErrorCode:{code=%s, msg=%s} ", code, msg);
    }
}
