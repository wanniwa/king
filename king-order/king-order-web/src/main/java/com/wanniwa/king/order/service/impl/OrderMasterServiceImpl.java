package com.wanniwa.king.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanniwa.king.order.entity.OrderMaster;
import com.wanniwa.king.order.mapper.OrderMasterMapper;
import com.wanniwa.king.order.service.OrderMasterService;
import org.springframework.stereotype.Service;

@Service
public class OrderMasterServiceImpl extends ServiceImpl<OrderMasterMapper, OrderMaster> implements OrderMasterService{

}
