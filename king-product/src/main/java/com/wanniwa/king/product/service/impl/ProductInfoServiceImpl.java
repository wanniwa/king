package com.wanniwa.king.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanniwa.king.product.entity.ProductInfo;
import com.wanniwa.king.product.mapper.ProductInfoMapper;
import com.wanniwa.king.product.service.ProductInfoService;
@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements ProductInfoService{

    @Override
    public List<ProductInfo> findUpAll() {

        LambdaQueryWrapper<ProductInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductInfo::getProductStatus, 0);
        return list(queryWrapper);
    }
}
