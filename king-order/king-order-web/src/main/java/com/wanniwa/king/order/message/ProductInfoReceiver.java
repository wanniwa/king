package com.wanniwa.king.order.message;

import cn.hutool.json.JSONUtil;
import com.wanniwa.king.product.entity.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收方
 * @author 73493
 */
@Slf4j
@Component
public class ProductInfoReceiver {

    /**
     * 数码供应商 接收消息
     * @param msg
     */
    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String msg) {
        ProductInfo productInfo = JSONUtil.toBean(msg, ProductInfo.class);
        log.info("从队列[{}]接收到消息productInfo :{}","productInfo",productInfo);
    }


}
