package com.wanniwa.king.order.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
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
    public void finish(String orderId) {

    }
}
