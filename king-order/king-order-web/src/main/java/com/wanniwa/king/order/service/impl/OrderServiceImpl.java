package com.wanniwa.king.order.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.wanniwa.king.common.enums.ResultEnum;
import com.wanniwa.king.common.exception.ResultException;
import com.wanniwa.king.order.dto.OrderDTO;
import com.wanniwa.king.order.entity.OrderDetail;
import com.wanniwa.king.order.entity.OrderMaster;
import com.wanniwa.king.order.enums.OrderStatusEnum;
import com.wanniwa.king.order.enums.PayStatusEnum;
import com.wanniwa.king.order.mapper.OrderMasterMapper;
import com.wanniwa.king.order.service.OrderDetailService;
import com.wanniwa.king.order.service.OrderService;
import com.wanniwa.king.product.client.ProductClient;
import com.wanniwa.king.product.dto.CartDTO;
import com.wanniwa.king.product.entity.ProductInfo;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderMasterMapper orderMasterMapper;
    @Autowired
    private ProductClient productClient;


    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = IdWorker.getIdStr();
        //TODO 查询商品信息(调用商品服务)
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfo> productInfoList = productClient.listForOrder(productIdList);

        //TODO 计算总价
        BigDecimal orderAmount = BigDecimal.ZERO;
        for (int i = 0; i < orderDTO.getOrderDetailList().size(); i++) {
            OrderDetail orderDetail = orderDTO.getOrderDetailList().get(i);
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    orderAmount = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(orderId + "-" + i);
                }
            }
        }

        //TODO 扣库存(调用商品服务)
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderId(orderId);
        orderMasterMapper.insert(orderMaster);

        orderDetailService.saveBatch(orderDTO.getOrderDetailList());
        orderDTO.setOrderId(orderId);
        return orderDTO;
    }

    @Override
    public OrderDTO finish(String orderId) {
        //1查询订单
        OrderMaster orderMaster = orderMasterMapper.selectById(orderId);
        if (orderMaster == null) {
            throw new ResultException(ResultEnum.ORDER_NOT_EXIST);
        }
        //2判断订单状态
        if (orderMaster.getOrderStatus() != OrderStatusEnum.NEW.getCode()) {
            throw new ResultException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //3修改订单状态为完结
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterMapper.updateById(orderMaster);
        //查询订单详情
        List<OrderDetail> orderDetailList = orderDetailService.findByOrderId(orderId);
        if (Collections.isEmpty(orderDetailList)) {
            throw new ResultException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
