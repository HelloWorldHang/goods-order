package com.brady.order.service.impl;

import com.brady.order.common.MQConstant;
import com.brady.order.dto.OrderDTO;
import com.brady.order.entity.Order;
import com.brady.order.mapper.OrderMapper;
import com.brady.order.service.OrderService;
import com.brady.order.utils.BeanConvertUtils;
import com.brady.order.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author brady.si
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderMapper orderMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public boolean submitOrder(OrderDTO.SubmitOrderDTO dto) {
        Order order = BeanConvertUtils.copyProperties(dto, Order.class);
        if (order != null){
            order.setCreateTime(DateUtil.getCurrentSeconds());
            int res = orderMapper.insertSelective(order);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = sdf.format(new Date());
            log.info("------------订单创建成功orderId:{}time:{}", order.getId(), time);
            if (res == 1){
                // 发消息到死信队列
                rabbitTemplate.convertAndSend(MQConstant.PAY_ORDER_CREATE_FANOUT_EXCHANGE, "", order);
                return true;
            }
            return false;
        }
        return false;
    }
}
