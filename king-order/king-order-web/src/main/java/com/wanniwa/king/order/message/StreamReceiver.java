package com.wanniwa.king.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {
    @StreamListener(StreamClient.INPUT)
    @SendTo(StreamClient.OUTPUT)
    public String myMessageInput(Object message) {
        log.info("myMessageInput StreamReceiver:{}",message);
        return "收到";
    }
    @StreamListener(StreamClient.OUTPUT)
    public void myMessageOutput(String message) {
        log.info("myMessageOutput StreamReceiver:{}",message);
    }
}
