package com.brady.order.controller;

import com.brady.order.dto.OrderDTO;
import com.brady.order.export.OrderApi;
import com.brady.order.service.OrderService;
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
    OrderService orderService;

    @Override
    public boolean submitOrder(@Validated @RequestBody OrderDTO.SubmitOrderDTO dto) {
        boolean res = orderService.submitOrder(dto);
        return res;
    }
}
