package com.wanniwa.king.product.enums;

import com.wanniwa.king.common.utils.ICodeMsg;
import lombok.Getter;

@Getter
public enum  ProductResultEnum implements ICodeMsg {
    PRODUCT_NOT_EXIST(1,"商品不存在"),
    PRODUCT_STOCK_ERROR(1,"库存有误"),
    ;

    private int code;
    private String msg;

    ProductResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
