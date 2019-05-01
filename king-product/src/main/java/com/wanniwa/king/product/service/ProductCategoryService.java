package com.wanniwa.king.product.service;

import com.wanniwa.king.product.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

public interface ProductCategoryService extends IService<ProductCategory>{


    List<ProductCategory> findByCategoryTypeIn(Set<Integer> categoryTypeIdList);
}
