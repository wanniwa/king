package com.wanniwa.king.order.converter;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.wanniwa.king.common.enums.ResultEnum;
import com.wanniwa.king.common.exception.ResultException;
import com.wanniwa.king.order.dto.OrderDTO;
import com.wanniwa.king.order.entity.OrderDetail;
import com.wanniwa.king.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = JSON.parseArray(orderForm.getItems(),OrderDetail.class);
        } catch (Exception e) {
            log.error("【json转换】错误, string={}", orderForm.getItems());
            throw new ResultException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
