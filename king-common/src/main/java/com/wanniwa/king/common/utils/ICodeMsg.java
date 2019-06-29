
package com.wanniwa.king.common.utils;

/**
 * 错误码接口
 * @author wanniwa
 */
public interface ICodeMsg {


    /**
     * 获得编码
     * @return  编码
     */
    int getCode();

    /**
     * 错误描述
     * @return  错误描述
     */
    String getMsg();
}
