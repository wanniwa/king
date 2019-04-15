
package com.wanniwa.king.common.utils;

import lombok.Data;

/**
 * 查询参数
 *
 * @author Mark sunlightcs@gmail.com
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
    /**
     * SQL 排序 ASC 数组
     */
    private String[] ascs;
    /**
     * SQL 排序 DESC 数组
     */
    private String[] descs;


}
