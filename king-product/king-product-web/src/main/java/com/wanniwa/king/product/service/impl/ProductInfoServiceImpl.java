package com.wanniwa.king.product.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanniwa.king.common.exception.ResultException;
import com.wanniwa.king.product.dto.CartDTO;
import com.wanniwa.king.product.entity.ProductInfo;
import com.wanniwa.king.product.enums.ProductResultEnum;
import com.wanniwa.king.product.enums.ProductStatusEnum;
import com.wanniwa.king.product.mapper.ProductInfoMapper;
import com.wanniwa.king.product.service.ProductInfoService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements ProductInfoService{
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Override
    public List<ProductInfo> findUpAll() {
        LambdaQueryWrapper<ProductInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductInfo::getProductStatus, ProductStatusEnum.UP.getCode());
        return list(queryWrapper);
    }

    @Override

    public void decreaseStock(List<CartDTO> cartDTOList) {
        List<ProductInfo> productInfoList = decreaseStockProcess(cartDTOList);
        amqpTemplate.convertAndSend("productInfo", JSONUtil.toJsonStr(productInfoList));

    }
    @Transactional(rollbackFor = Exception.class)
    public List<ProductInfo> decreaseStockProcess(List<CartDTO> cartDTOList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = this.getById(cartDTO.getProductId());
            //判断商品是否存在
            if (null == productInfo) {
                throw new ResultException(ProductResultEnum.PRODUCT_NOT_EXIST);
            }
            //判断库存是否足够
            int result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result<0) {
                throw new ResultException(ProductResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            this.updateById(productInfo);
            productInfoList.add(productInfo);
        }
        return productInfoList;
    }
}
