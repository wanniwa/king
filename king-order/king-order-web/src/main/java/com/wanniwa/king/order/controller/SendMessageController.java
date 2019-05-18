package com.wanniwa.king.order.controller;

import com.wanniwa.king.order.dto.OrderDTO;
import com.wanniwa.king.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;
    //@GetMapping("/sendMessage")
    //public void process() {
    //    Message<String> message = MessageBuilder.withPayload("now" + new Date()).build();
    //    streamClient.output().send(message);
    //}
    @GetMapping("/sendMessage")
    public void process() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("123456");
        Message<OrderDTO> message = MessageBuilder.withPayload(orderDTO).build();
        streamClient.output().send(message);
    }
}
