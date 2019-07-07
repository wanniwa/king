package com.wanniwa.king.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanniwa.king.common.enums.ResultEnum;
import com.wanniwa.king.common.exception.ResultException;
import com.wanniwa.king.order.entity.OrderDetail;
import com.wanniwa.king.order.mapper.OrderDetailMapper;
import com.wanniwa.king.order.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService{

    @Override
    public List<OrderDetail> findByOrderId(String orderId) {
        if (orderId == null) {
            throw new ResultException(ResultEnum.PARAM_ERROR);
        }
        LambdaQueryWrapper<OrderDetail> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderDetail::getOrderId, orderId);
        return this.list(wrapper);
    }
}
