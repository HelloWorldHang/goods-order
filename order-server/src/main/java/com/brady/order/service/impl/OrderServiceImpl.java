package com.brady.order.service.impl;

import com.brady.order.dto.OrderDTO;
import com.brady.order.entity.Order;
import com.brady.order.mapper.OrderMapper;
import com.brady.order.service.OrderService;
import com.brady.order.util.BeanConvertUtils;
import com.brady.order.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author brady.si
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderMapper orderMapper;

    @Override
    public boolean submitOrder(OrderDTO.SubmitOrderDTO dto) {
        Order order = BeanConvertUtils.copyProperties(dto, Order.class);
        if (order != null){
            order.setCreateTime(DateUtil.getCurrentSeconds());
            int res = orderMapper.insertSelective(order);
            return res == 1 ? true : false;
        }
        return false;
    }
}
