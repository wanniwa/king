package com.wanniwa.king.common.data.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 分页工具
 *
 * @author wanniwa
 */
public class PageUtil {
    public static <T> Page<T> getPage(PageQuery<T> pageQuery) {
        return new Page<>(pageQuery.getCurrent(), pageQuery.getSize());
    }
}
