package com.wanniwa.king.common.core.utils;

import com.wanniwa.king.common.core.enums.ResultEnum;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.Serializable;

/**
 * 通用的返回类型
 *
 * @author wanniwa
 */
@Data
@Builder
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

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
        return fail(ResultEnum.FAILED);
    }

    public static <T> R<T> fail(String msg) {
        return R.<T>builder().code(ResultEnum.FAILED.getCode()).msg(msg).build();
    }

    public static <T> R<T> fail(int code, String msg) {
        return R.<T>builder().code(code).msg(msg).build();
    }

    /**
     * 接收通用错误码接口
     *
     * @param iCodeMsg 定义枚举
     * @return R
     */
    public static <T> R<T> fail(ICodeMsg iCodeMsg) {
        return R.<T>builder().code(iCodeMsg.getCode()).msg(iCodeMsg.getMsg()).build();
    }

    /**
     * 数据自动校验
     *
     * @param bindingResult General interface that represents binding results
     * @return R
     */
    public static <T> R<T> fail(BindingResult bindingResult) {
        FieldError fieldError = bindingResult.getFieldError();
        assert fieldError != null;
        return R.fail(ResultEnum.FAILED.getCode(), fieldError.getField() + fieldError.getDefaultMessage());
    }
}
