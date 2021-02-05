package com.brady.order.config;

import com.brady.order.common.MQConstant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author brady.si
 */
@Configuration
public class RabbitQueueConfiguration {
    
    @Bean
    public MessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 订单创建扇形交换机
     * @return
     */
    @Bean
    public FanoutExchange orderCreateFanoutExchange(){
        return new FanoutExchange(MQConstant.PAY_ORDER_CREATE_FANOUT_EXCHANGE, true, false);
    }

    /**
     * 订单创建死信交换机
     * @return
     */
    @Bean
    public DirectExchange orderCreateDeadLetterExchange(){
        return new DirectExchange(MQConstant.PAY_ORDER_CREATE_DEAD_LETTER_EXCHANGE, true, false);
    }

    /**
     * 订单创建 -- 优惠券消费队列
     * @return
     */
    @Bean
    public Queue orderCreateCouponQueue(){
        return new Queue(MQConstant.PAY_ORDER_CREATE_COUPON_QUEUE,true);
    }

    /**
     * 订单创建 -- 死信队列
     * @return
     */
    @Bean
    public Queue orderCreateDeadLetterQueue(){
        Map<String, Object> arguments = new HashMap<>();
        // 设置超时后的交换机
        arguments.put("x-dead-letter-exchange", MQConstant.PAY_ORDER_CREATE_DEAD_LETTER_EXCHANGE);
        // 设置routing-key
        arguments.put("x-dead-letter-routing-key", MQConstant.PAY_ORDER_CREATE_EXPIRE_ROUTING_KEY);
        arguments.put("x-message-ttl", MQConstant.EXPIRE_MILL);
        // 是否持久化
        boolean durable = true;
        // 仅创建者可以使用的私有队列，断开后自动删除
        boolean exclusive = false;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;
        return new Queue(MQConstant.PAY_ORDER_CREATE_DEAD_LETTER_QUEUE,durable, exclusive, autoDelete, arguments);
    }

    /**
     * 订单创建 -- 过期消费队列
     * @return
     */
    @Bean
    public Queue orderCreateExpireQueue() {
        Queue queue = new Queue(MQConstant.PAY_ORDER_CREATE_EXPIRE_QUEUE,true,false,false);
        return queue;
    }

    /**
     * {订单创建扇形交换机}绑定{优惠券消费队列}
     * @return
     */
    @Bean
    public Binding orderCreateCouponBinding() {
        return BindingBuilder.bind(orderCreateCouponQueue()).to(orderCreateFanoutExchange());
    }

    /**
     * {订单创建扇形交换机}绑定{死信队列}
     * @return
     */
    @Bean
    public Binding orderCreateDeadLetterBinding() {
        return BindingBuilder.bind(orderCreateDeadLetterQueue()).to(orderCreateFanoutExchange());
    }

    /**
     * {死信队列}绑定{订单创建死信交换机}
     * @return
     */
    @Bean
    public Binding orderCreateDeadLetterExpireBinding() {
        return BindingBuilder.bind(orderCreateDeadLetterQueue()).to(orderCreateDeadLetterExchange()).withQueueName();
    }

    /**
     * {订单创建死信交换机}通过{死信队列中的路由键}与{过期消费队列}绑定起来
     * @return
     */
    @Bean
    public Binding orderCreateExpireBinding() {
        // 设置Binding key
        return BindingBuilder.bind(orderCreateExpireQueue()).to(orderCreateDeadLetterExchange()).with(MQConstant.PAY_ORDER_CREATE_EXPIRE_ROUTING_KEY);
    }
}
