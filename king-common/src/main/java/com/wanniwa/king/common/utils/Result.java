package com.wanniwa.king.common.utils;

import com.wanniwa.king.common.enums.ResultEnum;
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
public class Result<T> {

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

    public static Result ok() {
        return ok(null);
    }

    public static <T> Result<T> ok(T data) {
        return Result.<T>builder().code(ResultEnum.SUCCESS.getCode()).msg(ResultEnum.SUCCESS.getMsg()).data(data).build();
    }

    public static Result error() {
        return error(ResultEnum.FAILED.getCode(), "未知异常，请联系管理员");
    }

    public static Result error(int code, String msg) {
        return Result.builder().code(code).msg(ResultEnum.FAILED.getMsg()).build();
    }

    /**
     * 接收ICodeMsg子类
     * @param iCodeMsg
     * @return
     */
    public static Result error(ICodeMsg iCodeMsg) {
        return Result.builder().code(iCodeMsg.getCode()).msg(iCodeMsg.getMsg()).build();
    }

    /**
     * 数据自动校验
     * @param bindingResult
     * @return
     */
    public static Result error(BindingResult bindingResult) {
        FieldError fieldError = bindingResult.getFieldError();
        assert fieldError != null;
        return Result.error(ResultEnum.FAILED.getCode(), fieldError.getField() + fieldError.getDefaultMessage());
    }
}
