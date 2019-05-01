package com.wanniwa.king.order.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanniwa.king.order.entity.OrderDetail;
import com.wanniwa.king.order.mapper.OrderDetailMapper;
import com.wanniwa.king.order.service.OrderDetailService;
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService{

}
