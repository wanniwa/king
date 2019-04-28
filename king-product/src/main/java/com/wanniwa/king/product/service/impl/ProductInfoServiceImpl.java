package com.wanniwa.king.product.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanniwa.king.product.entity.ProductInfo;
import com.wanniwa.king.product.mapper.ProductInfoMapper;
import com.wanniwa.king.product.service.ProductInfoService;

@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements ProductInfoService {

}

