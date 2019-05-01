package com.wanniwa.king.order.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanniwa.king.order.entity.OrderMaster;
import com.wanniwa.king.order.mapper.OrderMasterMapper;
import com.wanniwa.king.order.service.OrderMasterService;
@Service
public class OrderMasterServiceImpl extends ServiceImpl<OrderMasterMapper, OrderMaster> implements OrderMasterService{

}
