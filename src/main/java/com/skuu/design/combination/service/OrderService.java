package com.skuu.design.combination.service;

import com.skuu.design.combination.event.OrderEvent;
import com.skuu.design.combination.event.OrderEventPublisher;
import com.skuu.design.combination.event.listeners.*;
import com.skuu.design.combination.factory.OrderFactory;
import com.skuu.design.combination.model.Order;
import com.skuu.design.combination.model.OrderItem;
import com.skuu.design.combination.state.OrderContext;
import com.skuu.design.combination.strategy.*;
import com.skuu.design.combination.validator.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dcx
 * @description 订单服务 - 组合多种设计模式
 * @create 2025-01-27
 */
public class OrderService {
    private Map<String, PaymentStrategy> paymentStrategies = new HashMap<>();
    private OrderValidator validatorChain;
    private OrderEventPublisher eventPublisher;

    public OrderService() {
        // 初始化支付策略
        paymentStrategies.put("alipay", new AlipayStrategy());
        paymentStrategies.put("wechat", new WechatPayStrategy());

        // 初始化校验链
        validatorChain = new StockValidator();
        validatorChain.setNext(new PriceValidator())
                      .setNext(new AddressValidator());

        // 初始化事件发布器
        eventPublisher = new OrderEventPublisher();
        eventPublisher.subscribe(new InventoryListener());
        eventPublisher.subscribe(new NotificationListener());
        eventPublisher.subscribe(new PointListener());
    }

    /**
     * 创建并支付订单
     * 这个方法组合了多个设计模式
     */
    public Order createAndPayOrder(String userId, List<OrderItem> items,
                                  String address, String paymentType) {
        System.out.println("\n========== 开始处理订单 ==========");

        // 1. 工厂模式 + 建造者模式：创建订单
        System.out.println("\n【步骤1】创建订单");
        Order order = OrderFactory.createOrder(userId, items, address);
        System.out.println("订单ID: " + order.getOrderId());
        System.out.println("订单金额: ¥" + order.getTotalAmount());

        // 2. 责任链模式：校验订单
        System.out.println("\n【步骤2】校验订单");
        try {
            validatorChain.validate(order);
            System.out.println("✅ 订单校验通过");
        } catch (Exception e) {
            System.out.println("❌ 订单校验失败: " + e.getMessage());
            return null;
        }

        // 3. 策略模式：执行支付
        System.out.println("\n【步骤3】执行支付");
        PaymentStrategy strategy = paymentStrategies.get(paymentType);
        if (strategy == null) {
            System.out.println("❌ 不支持的支付方式: " + paymentType);
            return null;
        }

        System.out.println("支付方式: " + strategy.getPaymentType());
        PaymentResult result = strategy.pay(order, order.getTotalAmount());

        if (result.isSuccess()) {
            System.out.println("✅ " + result.getMessage());
            System.out.println("交易号: " + result.getTransactionId());

            // 4. 状态模式：改变订单状态
            System.out.println("\n【步骤4】更新订单状态");
            OrderContext context = new OrderContext(order);
            context.pay();

            // 5. 观察者模式：发布支付成功事件
            System.out.println("\n【步骤5】发布订单支付事件");
            eventPublisher.publishEvent(new OrderEvent("ORDER_PAID", order));

            System.out.println("\n========== 订单处理完成 ==========");
        } else {
            System.out.println("❌ 支付失败: " + result.getMessage());
        }

        return order;
    }
}

