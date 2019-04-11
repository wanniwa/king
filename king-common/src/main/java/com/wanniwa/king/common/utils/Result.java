package com.wanniwa.king.common.utils;

import com.wanniwa.king.common.enums.ResultEnum;
import lombok.Data;

@Data
public class Result {

    // 状态码
    private int code;
    // 提示信息
    private String msg;
    // 响应数据
    private Object data;


    private Result() {
        super();
    }

    private Result(int code) {
        super();
        this.code = code;
        this.msg = ResultEnum.getMsgByCode(code);
    }

    private Result(ResultEnum resultEnum) {
        super();
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    private Result(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    private Result(Integer code, String msg, Object data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success() {
        return new Result(ResultEnum.SUCCESS);
    }

    public static Result success(String msg) {
        success().setMsg(msg);
        return new Result(ResultEnum.SUCCESS.getCode(), msg);
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
