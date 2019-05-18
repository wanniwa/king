package com.wanniwa.king.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收方
 */
@Slf4j
@Component
public class MqReceiver {
    //1. @RabbitListener(queues = "myQueue")
    //2. 自动创建队列 @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    //3 自动创建Exchange和Queue绑定
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void process(String msg) {
        log.info("MqReceiver:{}",msg);
    }

    /**
     * 数码供应商 接收消息
     * @param msg
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",
            value = @Queue("computerOrder")
    ))
    public void processComputer(String msg) {
        log.info("computer MqReceiver:{}",msg);
    }

    /**
     * 水果供应商 接收消息
     * @param msg
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            value = @Queue("fruitOrder"),
            key = "fruit"
    ))
    public void processFruit(String msg) {
        log.info("fruit MqReceiver:{}",msg);
    }

}
