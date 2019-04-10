
package com.wanniwa.king.common.utils;

/**
 * 错误码接口
 */
public interface IErrorCode {

    /**
     * 错误编码 -1、失败 0、成功
     */
    int getCode();

    /**
     * 错误描述
     */
    String getMsg();
}
