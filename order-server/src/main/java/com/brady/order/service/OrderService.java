package com.brady.order.service;


import com.brady.order.dto.OrderDTO;

/**
 * @author brady.si
 */
public interface OrderService {

    /**
     * 提交订单接口
     * @param dto
     */
    boolean submitOrder(OrderDTO.SubmitOrderDTO dto);

}
