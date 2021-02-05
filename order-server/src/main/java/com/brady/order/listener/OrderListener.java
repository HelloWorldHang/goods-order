package com.brady.order.listener;

import com.brady.order.common.MQConstant;
import com.brady.order.dto.OrderDTO;
import com.brady.order.entity.Order;
import com.brady.order.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author brady.si
 */
@Component
@Slf4j
public class OrderListener {

    @Resource
    OrderMapper orderMapper;

    @RabbitListener(queues = MQConstant.PAY_ORDER_CREATE_EXPIRE_QUEUE)
    public void expireOrder(Order message) {
        Order order = new Order();
        order.setId(message.getId());
        // 更新订单状态为关闭
        order.setStatus((byte)2);
        orderMapper.updateByPrimaryKeySelective(order);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        log.info("订单已关闭，orderId:{},time:{}", order.getId(), time);
    }

    @RabbitListener(queues = MQConstant.PAY_ORDER_CREATE_COUPON_QUEUE)
    public void test(Order message) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        System.out.println("订单创建发送优惠券：" + time + " 收到消息," + message.getGoodsName());
    }
}
