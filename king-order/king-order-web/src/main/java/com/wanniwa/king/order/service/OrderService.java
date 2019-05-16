package com.wanniwa.king.order.service;

import com.wanniwa.king.order.dto.OrderDTO;

public interface OrderService {
    OrderDTO create(OrderDTO orderDTO);

    void finish(String orderId);
}
