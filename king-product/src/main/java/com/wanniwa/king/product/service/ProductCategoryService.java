package com.wanniwa.king.product.service;

import com.wanniwa.king.product.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ProductCategoryService extends IService<ProductCategory>{


    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeIdList);
}
