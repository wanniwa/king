package com.wanniwa.king.common.core.utils;

import com.wanniwa.king.common.core.constant.enums.ResultEnum;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * 通用的返回类型
 *
 * @author wanniwa
 */
@Data
@Builder
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

    public static <T> R<T> error() {
        return error(ResultEnum.FAILED);
    }

    public static <T> R<T> error(String msg) {
        return R.<T>builder().code(ResultEnum.FAILED.getCode()).msg(msg).build();
    }

    public static <T> R<T> error(int code, String msg) {
        return R.<T>builder().code(code).msg(msg).build();
    }

    /**
     * 接收通用错误码接口
     *
     * @param iCodeMsg 定义枚举
     * @return R
     */
    public static <T> R<T> error(ICodeMsg iCodeMsg) {
        return R.<T>builder().code(iCodeMsg.getCode()).msg(iCodeMsg.getMsg()).build();
    }

    /**
     * 数据自动校验
     *
     * @param bindingResult  General interface that represents binding results
     * @return R
     */
    public static <T> R<T> error(BindingResult bindingResult) {
        FieldError fieldError = bindingResult.getFieldError();
        assert fieldError != null;
        return R.error(ResultEnum.FAILED.getCode(), fieldError.getField() + fieldError.getDefaultMessage());
    }
}
