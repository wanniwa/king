package com.wanniwa.king.common.exception;


import com.wanniwa.king.common.enums.ResultEnum;
import com.wanniwa.king.common.utils.IErrorCode;
import lombok.Getter;

/**
 * 自定义异常对象
 */
@Getter
public class ResultException extends RuntimeException {

    private Integer code;

    public ResultException() {
        super(ResultEnum.FAILED.getMsg());
    }
    /**
     * 统一异常处理
     * @param resultEnum 状态枚举
     */
    public ResultException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    /**
     * 统一异常处理
     * @param iErrorCode 枚举类型，需要实现结果枚举接口
     */
    public ResultException(IErrorCode iErrorCode) {
        super(iErrorCode.getMsg());
        this.code = iErrorCode.getCode();
    }

    /**
     * 统一异常处理
     * @param code 状态码
     * @param msg 提示信息
     */
    public ResultException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

}
