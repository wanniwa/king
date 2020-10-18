package com.wanniwa.king.common.core.utils;

import com.wanniwa.king.common.core.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用的返回类型
 *
 * @author wanniwa
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class R<T> {
    /**
     * 状态码
     */
    private int code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 响应数据
     */
    private T data;

    public static <T> R<T> ok() {
        return R.<T>builder().code(ResultEnum.SUCCESS.getCode()).msg(ResultEnum.SUCCESS.getMsg()).build();
    }

    public static <T> R<T> ok(T data) {
        return R.<T>builder().code(ResultEnum.SUCCESS.getCode()).msg(ResultEnum.SUCCESS.getMsg()).data(data).build();
    }

    public static <T> R<T> ok(String msg, T data) {
        return R.<T>builder().code(ResultEnum.SUCCESS.getCode()).msg(msg).data(data).build();
    }

    public static <T> R<T> fail() {
        return R.<T>builder().code(ResultEnum.FAILED.getCode()).msg(ResultEnum.FAILED.getMsg()).build();
    }

    public static <T> R<T> fail(String msg) {
        return R.<T>builder().code(ResultEnum.FAILED.getCode()).msg(msg).build();
    }

    public static <T> R<T> fail(String msg, T data) {
        return R.<T>builder().code(ResultEnum.FAILED.getCode()).msg(msg).data(data).build();
    }

    public static <T> R<T> fail(int code, String msg) {
        return R.<T>builder().code(code).msg(msg).build();
    }
}
