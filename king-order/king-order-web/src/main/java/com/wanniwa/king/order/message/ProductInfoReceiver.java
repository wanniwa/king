package com.wanniwa.king.order.message;

import com.alibaba.fastjson.JSON;
import com.wanniwa.king.product.entity.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 接收方
 * @author 73493
 */
@Slf4j
@Component
public class ProductInfoReceiver {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";
    /**
     * 数码供应商 接收消息
     * @param msg
     */
    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String msg) {
        List<ProductInfo> productInfoList = JSON.parseArray(msg, ProductInfo.class);
        log.info("从队列[{}]接收到消息productInfo :{}","productInfo",productInfoList);
        for (ProductInfo productInfo : productInfoList) {
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE,productInfo.getProductId()),
                    String.valueOf(productInfo.getProductStock()));
        }

    }


}
