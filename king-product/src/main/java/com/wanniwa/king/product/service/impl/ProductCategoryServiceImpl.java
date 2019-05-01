package com.wanniwa.king.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanniwa.king.product.mapper.ProductCategoryMapper;
import com.wanniwa.king.product.entity.ProductCategory;
import com.wanniwa.king.product.service.ProductCategoryService;
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService{

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeIdList) {
        LambdaQueryWrapper<ProductCategory> queryWrapper= new LambdaQueryWrapper<>();
        queryWrapper.in(ProductCategory::getCategoryId, categoryTypeIdList);
        return list(queryWrapper);
    }
}
