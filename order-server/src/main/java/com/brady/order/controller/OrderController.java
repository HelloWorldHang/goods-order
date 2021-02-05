package com.brady.order.controller;

import com.brady.order.common.MQConstant;
import com.brady.order.dto.OrderDTO;
import com.brady.order.export.OrderApi;
import com.brady.order.service.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author brady.si
 */
@RestController
public class OrderController implements OrderApi {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public boolean submitOrder(@Validated @RequestBody OrderDTO.SubmitOrderDTO dto) {
        boolean res = orderService.submitOrder(dto);
        return res;
    }

    @Override
    public boolean mqTest(@Validated @RequestBody OrderDTO.TestOrder dto) {
        rabbitTemplate.convertAndSend(MQConstant.PAY_ORDER_CREATE_FANOUT_EXCHANGE, "", dto);
        return true;
    }
}
