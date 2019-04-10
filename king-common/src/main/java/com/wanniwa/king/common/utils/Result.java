package com.wanniwa.king.common.utils;

import lombok.Data;

@Data
public class Result {

    // 状态码
    private int code;
    // 提示信息
    private String msg;
    // 响应数据
    private Object data;

    public static Result success(){
        return new Result();
    }
}
