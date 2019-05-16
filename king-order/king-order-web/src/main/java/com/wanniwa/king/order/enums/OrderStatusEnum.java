package com.wanniwa.king.order.enums;

import com.wanniwa.king.common.utils.ICodeMsg;
import lombok.Getter;

@Getter
public enum OrderStatusEnum implements ICodeMsg {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "取消"),;
    private int code;
    private String msg;

    OrderStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
