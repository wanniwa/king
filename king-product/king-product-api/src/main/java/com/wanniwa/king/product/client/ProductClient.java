package com.wanniwa.king.product.client;

import com.wanniwa.king.product.dto.CartDTO;
import com.wanniwa.king.product.entity.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "king-product",fallback = ProductClientFallback.class)
public interface ProductClient {
    @PostMapping("/product/listForOrder")
    List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);

    @GetMapping("/msg")
    String productMsg();

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<CartDTO> cartDTOList);

}

@Component
class ProductClientFallback implements ProductClient{
    @Override
    public List<ProductInfo> listForOrder(List<String> productIdList) {
        return null;
    }

    @Override
    public String productMsg() {
        return null;
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {

    }
}