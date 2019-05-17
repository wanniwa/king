package com.wanniwa.king.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
@Slf4j
public class MqReceiver {
    @RabbitListener(queues = "myQueue")
    public void process(String msg) {
        log.info(msg);
    }
}
