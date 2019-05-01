package com.wanniwa.king.order.service.impl;

import com.wanniwa.king.order.dto.OrderDTO;
import com.wanniwa.king.order.entity.OrderMaster;
import com.wanniwa.king.order.enums.OrderStatusEnum;
import com.wanniwa.king.order.enums.PayStatusEnum;
import com.wanniwa.king.order.mapper.OrderDetailMapper;
import com.wanniwa.king.order.mapper.OrderMasterMapper;
import com.wanniwa.king.order.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private OrderMasterMapper orderMasterMapper;
    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        //TODO 查询商品信息(调用商品服务)
        //TODO 计算总价
        //TODO 扣库存(调用商品服务)

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterMapper.insert(orderMaster);
        orderDTO.setOrderId(orderMaster.getOrderId());
        return orderDTO;
    }

    @Override
    public void finish(String orderId) {

    }
}
