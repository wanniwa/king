package com.wanniwa.king.product.service.impl;

import com.wanniwa.king.product.ProductCommonTest;
import com.wanniwa.king.product.dto.CartDTO;
import com.wanniwa.king.product.service.ProductInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class ProductInfoServiceImplTest extends ProductCommonTest {
    @Autowired
    private ProductInfoService productInfoService;
    @Test
    public void findUpAll() {
    }

    @Test
    public void decreaseStock() {
        CartDTO cartDTO = new CartDTO("164103465734242707",2);
        productInfoService.decreaseStock(Collections.singletonList(cartDTO));

    }
}