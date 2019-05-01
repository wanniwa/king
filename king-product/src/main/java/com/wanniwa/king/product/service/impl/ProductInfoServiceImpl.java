package com.wanniwa.king.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanniwa.king.product.entity.ProductInfo;
import com.wanniwa.king.product.enums.ProductStatusEnum;
import com.wanniwa.king.product.mapper.ProductInfoMapper;
import com.wanniwa.king.product.service.ProductInfoService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements ProductInfoService{

    @Override
    public List<ProductInfo> findUpAll() {
        LambdaQueryWrapper<ProductInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductInfo::getProductStatus, ProductStatusEnum.UP.getCode());
        return list(queryWrapper);
    }
}
