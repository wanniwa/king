package com.wanniwa.king.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wanniwa.king.product.dto.CartDTO;
import com.wanniwa.king.product.entity.ProductInfo;

import java.util.List;

public interface ProductInfoService extends IService<ProductInfo>{


    List<ProductInfo> findUpAll();

    void decreaseStock(List<CartDTO> cartDTOList);

}