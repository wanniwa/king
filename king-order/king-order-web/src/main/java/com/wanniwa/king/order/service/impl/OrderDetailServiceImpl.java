package com.wanniwa.king.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanniwa.king.order.entity.OrderDetail;
import com.wanniwa.king.order.mapper.OrderDetailMapper;
import com.wanniwa.king.order.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService{

}
