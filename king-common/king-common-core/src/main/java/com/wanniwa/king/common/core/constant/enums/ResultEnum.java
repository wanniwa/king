package com.wanniwa.king.common.core.constant.enums;


import com.wanniwa.king.common.core.constant.CommonConstants;
import com.wanniwa.king.common.core.utils.ICodeMsg;

/**
 *
 * @author wanniwa
 */

public enum ResultEnum implements ICodeMsg {
    /**
     * 操作失败
     */
    FAILED(CommonConstants.SUCCESS, "操作失败"),
    /**
     * 成功
     */
    SUCCESS(CommonConstants.FAIL, "成功"),

    ;

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
        return String.format("ResultEnum:{code=%s, msg=%s}", code, msg);
    }
}
