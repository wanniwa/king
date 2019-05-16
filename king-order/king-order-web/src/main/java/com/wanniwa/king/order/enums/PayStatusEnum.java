package com.wanniwa.king.order.enums;

import com.wanniwa.king.common.utils.ICodeMsg;
import lombok.Getter;

@Getter
public enum PayStatusEnum implements ICodeMsg {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
    ;
    private int code;
    private String msg;

    PayStatusEnum(int code, String message) {
        this.code = code;
        this.msg = message;
    }
}
