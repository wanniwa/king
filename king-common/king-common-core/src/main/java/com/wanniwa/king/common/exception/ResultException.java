package com.wanniwa.king.common.exception;


import com.wanniwa.king.common.core.constant.enums.ResultEnum;
import com.wanniwa.king.common.core.utils.ICodeMsg;
import lombok.Getter;

/**
 * 自定义异常对象
 *
 * @author wanniwa
 */
@Getter
public class ResultException extends RuntimeException {

    private int code;

    private ResultException() {
        super(ResultEnum.FAILED.getMsg());
        this.code = ResultEnum.FAILED.getCode();
    }

    /**
     * 统一异常处理
     *
     * @param iErrorCode 枚举类型，需要实现结果枚举接口
     */
    public ResultException(ICodeMsg iErrorCode) {
        super(iErrorCode.getMsg());
        this.code = iErrorCode.getCode();
    }

    /**
     * 统一异常处理
     *
     * @param code 状态码
     * @param msg  提示信息
     */
    public ResultException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    /**
     * 统一异常处理
     *
     * @param msg 提示信息
     */
    public ResultException(String msg) {
        super(msg);
        this.code = ResultEnum.FAILED.getCode();
    }

}
