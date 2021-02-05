package com.brady.order.common;

/**
 * @author brady.si
 */
public class MQConstant {
    /** 订单创建 fanout(扇形) 交换机 */
    public final static String PAY_ORDER_CREATE_FANOUT_EXCHANGE = "pay.order.create.fanout.exchange";

    /** 订单创建 Dead Letter 交换机 */
    public final static String PAY_ORDER_CREATE_DEAD_LETTER_EXCHANGE = "pay.order.create.dead.letter.exchange";

    /** 订单创建优惠券订阅队列 */
    public final static String PAY_ORDER_CREATE_COUPON_QUEUE = "pay.order.create.coupon";

    /** 订单创建指定时间不支付死信队列 (TTL QUEUE) */
    public final static String PAY_ORDER_CREATE_DEAD_LETTER_QUEUE = "pay.order.create.dead.letter";

    /** 死信转发队列(DLX QUEUE) */
    public final static String PAY_ORDER_CREATE_EXPIRE_QUEUE = "pay.order.create.expire";

    public final static String PAY_ORDER_CREATE_EXPIRE_ROUTING_KEY = "pay.order.create.expire.routing.key";

    public final static int EXPIRE_MILL = 60 * 1000;
}
