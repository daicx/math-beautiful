package com.skuu.demo.lambdaordersystem.service;

import com.skuu.demo.lambdaordersystem.enums.OrderStatusEnum;
import com.skuu.demo.lambdaordersystem.event.OrderStatusChangedEvent;
import com.skuu.demo.lambdaordersystem.model.Order;
import com.skuu.demo.lambdaordersystem.state.LambdaOrderContext;
import com.skuu.demo.lambdaordersystem.state.StateMachineConfig;
import com.skuu.demo.lambdaordersystem.validator.TransitionRequest;
import com.skuu.demo.lambdaordersystem.validator.TransitionValidatorChain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * 订单服务（lambda 版）：创建/加载订单，校验链用 Predicate 组合
 */
public class LambdaOrderService {

    private final StateMachineConfig config;
    private final Predicate<TransitionRequest> validator;
    private final List<Order> orderRepository = new ArrayList<>();

    public LambdaOrderService() {
        this.config = StateMachineConfig.createDefault();
        this.validator = TransitionValidatorChain.chain(
            config.stateSupportPredicate(),
            TransitionValidatorChain.terminalState(),
            TransitionValidatorChain.paymentAmount()
        );
    }

    public LambdaOrderContext createOrder(String customerName, String userId,
                                          String productId, String productName, double amount) {
        String orderId = "ORD" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Order order = new Order(orderId, customerName, userId, productId, productName, amount);
        order.setStatus(OrderStatusEnum.PENDING_PAYMENT);
        orderRepository.add(order);

        LambdaOrderContext ctx = new LambdaOrderContext(order, config, validator);
        // 可选：用 lambda 注册监听
        ctx.addStatusChangeListener(LambdaOrderService::logStatusChange);
        ctx.addStatusChangeListener(LambdaOrderService::notifyUser);
        ctx.addStatusChangeListener(LambdaOrderService::inventoryEffect);

        System.out.println(String.format("订单创建成功: %s, 金额: %.2f 元", orderId, amount));
        return ctx;
    }

    public LambdaOrderContext loadOrder(String orderId) {
        Order order = orderRepository.stream()
            .filter(o -> orderId.equals(o.getOrderId()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("订单不存在: " + orderId));
        return new LambdaOrderContext(order, config, validator);
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orderRepository);
    }

    // ---------- 用 lambda 表达的“观察者” ----------
    private static void logStatusChange(OrderStatusChangedEvent e) {
        System.out.println(String.format("[日志] 订单 %s 状态: %s -> %s (时间: %d)",
            e.getOrder().getOrderId(), e.getPreviousStatus().getName(), e.getNewStatus().getName(),
            System.currentTimeMillis()));
    }

    private static void notifyUser(OrderStatusChangedEvent e) {
        System.out.println("[通知] 发送给用户 " + e.getOrder().getCustomerName() + ": 订单 " + e.getOrder().getOrderId()
            + " 状态已更新: " + e.getPreviousStatus().getName() + " -> " + e.getNewStatus().getName());
    }

    private static void inventoryEffect(OrderStatusChangedEvent e) {
        OrderStatusEnum prev = e.getPreviousStatus();
        OrderStatusEnum next = e.getNewStatus();
        if (next == OrderStatusEnum.PAID && prev == OrderStatusEnum.PENDING_PAYMENT) {
            System.out.println("[库存] 订单 " + e.getOrder().getOrderId() + " 已支付，扣减商品 " + e.getOrder().getProductId() + " 的库存");
        }
        if ((next == OrderStatusEnum.CANCELLED || next == OrderStatusEnum.REFUNDED)
            && prev != OrderStatusEnum.CANCELLED && prev != OrderStatusEnum.REFUNDED) {
            System.out.println("[库存] 订单 " + e.getOrder().getOrderId() + " 已取消/退款，恢复商品 " + e.getOrder().getProductId() + " 的库存");
        }
    }
}
