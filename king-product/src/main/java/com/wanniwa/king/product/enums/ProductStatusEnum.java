package com.wanniwa.king.product.enums;

public enum  ProductStatusEnum {
    UP(0,"上架"),
    DOWN(1,"下架")
    ;
    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
