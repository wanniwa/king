package com.wanniwa.king.common.core.utils;

import com.wanniwa.king.common.core.constant.enums.ResultEnum;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * 通用的返回类型
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

    public static R ok() {
        return ok(null);
    }

    public static <T> R<T> ok(T data) {
        return R.<T>builder().code(ResultEnum.SUCCESS.getCode()).msg(ResultEnum.SUCCESS.getMsg()).data(data).build();
    }

    public static R error() {
        return error(ResultEnum.FAILED);
    }

    public static R error(int code, String msg) {
        return R.builder().code(code).msg(ResultEnum.FAILED.getMsg()).build();
    }

    /**
     * 接收ICodeMsg子类
     * @param iCodeMsg
     * @return
     */
    public static R error(ICodeMsg iCodeMsg) {
        return R.builder().code(iCodeMsg.getCode()).msg(iCodeMsg.getMsg()).build();
    }

    /**
     * 数据自动校验
     * @param bindingResult
     * @return
     */
    public static R error(BindingResult bindingResult) {
        FieldError fieldError = bindingResult.getFieldError();
        assert fieldError != null;
        return R.error(ResultEnum.FAILED.getCode(), fieldError.getField() + fieldError.getDefaultMessage());
    }
}
