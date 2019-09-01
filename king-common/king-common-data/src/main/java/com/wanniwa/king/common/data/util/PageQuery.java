
package com.wanniwa.king.common.data.util;

import lombok.Data;

/**
 * 查询参数
 *
 * @author wanniwa
 */
@Data
public class PageQuery<T> {

    /**
     * 每页显示条数，默认 10
     */
    private long size = 10;
    /**
     * 当前页
     */
    private long current = 1;

}
