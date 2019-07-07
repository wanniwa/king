package com.wanniwa.king.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wanniwa.king.order.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService extends IService<OrderDetail>{
    /**
     * 根据订单号查询订单详情
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);


}
