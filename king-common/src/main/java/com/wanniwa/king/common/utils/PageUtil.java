package com.wanniwa.king.common.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 分页工具
 * @author wanniwa
 */
public class PageUtil {
    public static <T>  Page<T>  getPage(PageQuery<T>  pageQuery) {
        Page<T> page = new Page<>(pageQuery.getCurrent(),pageQuery.getSize());
        page.setAsc(pageQuery.getAscs());
        page.setDesc(pageQuery.getDescs());
        return page;
    }
}
