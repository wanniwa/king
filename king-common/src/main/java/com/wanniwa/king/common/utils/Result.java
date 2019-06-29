package com.wanniwa.king.common.utils;

import com.wanniwa.king.common.enums.ResultEnum;
import lombok.Data;

/**
 * 通用的返回类型
 * @author wanniwa
 */
@Data
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

    private Result() {
        super();
    }

    private Result(int code) {
        super();
        this.code = code;
        this.msg = ResultEnum.getMsgByCode(code);
    }
    private Result(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    private Result(int code, String msg, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private Result(ResultEnum resultEnum) {
        super();
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    private Result(ResultEnum resultEnum,T data) {
        super();
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
        this.data = data;
    }

    public static Result success() {
        return new Result(ResultEnum.SUCCESS);
    }

    public static Result success(String msg) {
        return new Result(ResultEnum.SUCCESS.getCode(), msg);
    }
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultEnum.SUCCESS, data);
    }

    public static Result error() {
        return error(ResultEnum.FAILED.getCode(), "未知异常，请联系管理员");
    }

    public static Result error(String msg) {
        return error(ResultEnum.FAILED.getCode(), msg);
    }

    public static Result error(int code, String msg) {
        return new Result(code, msg);
    }

    public static Result error(ResultEnum resultEnum) {
        return new Result(resultEnum);
    }

}
