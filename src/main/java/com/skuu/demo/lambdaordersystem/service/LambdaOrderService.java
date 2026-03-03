package com.skuu.demo.lambdaordersystem.service;

import com.skuu.demo.lambdaordersystem.enums.OrderStatusEnum;
import com.skuu.demo.lambdaordersystem.event.OrderStatusChangedEvent;
import com.skuu.demo.lambdaordersystem.model.Order;
import com.skuu.demo.lambdaordersystem.state.LambdaOrderContext;
import com.skuu.demo.lambdaordersystem.state.StateMachineConfig;
import com.skuu.demo.lambdaordersystem.validator.TransitionRequest;
import com.skuu.demo.lambdaordersystem.validator.TransitionValidatorChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * 订单服务（lambda 重构版）：校验链用 Predicate 组合，监听器用 Stream + 方法引用注册
 */
public class LambdaOrderService {

    private final StateMachineConfig config;
    private final Predicate<TransitionRequest> validator;
    private final List<Order> orderRepository = new ArrayList<>();

    /** 默认状态变更监听器（方法引用），便于用 Stream 注册 */
    private static final List<Consumer<OrderStatusChangedEvent>> DEFAULT_LISTENERS = Arrays.asList(
        LambdaOrderService::logStatusChange,
        LambdaOrderService::notifyUser,
        LambdaOrderService::inventoryEffect
    );

    public LambdaOrderService() {
        this.config = StateMachineConfig.createDefault();
        this.validator = Stream.of(
            config.stateSupportPredicate(),
            TransitionValidatorChain.terminalState(),
            TransitionValidatorChain.paymentAmount()
        ).reduce(Predicate::and).orElse(t -> true);
    }

    public LambdaOrderContext createOrder(String customerName, String userId,
                                          String productId, String productName, double amount) {
        String orderId = "ORD" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Order order = new Order(orderId, customerName, userId, productId, productName, amount);
        order.setStatus(OrderStatusEnum.PENDING_PAYMENT);
        orderRepository.add(order);

        LambdaOrderContext ctx = new LambdaOrderContext(order, config, validator);
        DEFAULT_LISTENERS.forEach(ctx::addStatusChangeListener);

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
