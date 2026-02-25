package com.skuu.demo.ordersystem.service;

import com.skuu.demo.ordersystem.factory.StateFactory;
import com.skuu.demo.ordersystem.model.Order;
import com.skuu.demo.ordersystem.enums.OrderStatusEnum;
import com.skuu.demo.ordersystem.state.OrderStateContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author dcx
 * @description 订单服务 - 业务层
 * 提供订单的创建、查询、状态操作等功能
 * @create 2025-01-27
 */
@Service
public class OrderService {

    private final StateFactory stateFactory;
    private final ApplicationEventPublisher eventPublisher;
    private final List<Order> orderRepository;

    @Autowired
    public OrderService(StateFactory stateFactory, ApplicationEventPublisher eventPublisher) {
        this.stateFactory = stateFactory;
        this.eventPublisher = eventPublisher;
        this.orderRepository = new ArrayList<>();
    }

    /** 非 Spring 环境（测试用） */
    public OrderService(StateFactory stateFactory, boolean testMode) {
        this.stateFactory = stateFactory;
        this.eventPublisher = null;
        this.orderRepository = new ArrayList<>();
    }
    
    /**
     * 创建订单
     */
    public OrderStateContext createOrder(String customerName, String userId,
                                         String productId, String productName, double amount) {
        String orderId = generateOrderId();
        Order order = new Order(orderId, customerName, userId, productId, productName, amount);
        order.setStatus(OrderStatusEnum.PENDING_PAYMENT);
        
        OrderStateContext context = new OrderStateContext(order, stateFactory, eventPublisher);

        // 保存订单
        orderRepository.add(order);
        
        System.out.println(String.format("订单创建成功: %s, 金额: %.2f 元", orderId, amount));
        return context;
    }
    
    /**
     * 根据订单ID加载订单
     */
    public OrderStateContext loadOrder(String orderId) {
        Order order = orderRepository.stream()
            .filter(o -> o.getOrderId().equals(orderId))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("订单不存在: " + orderId));
        
        return new OrderStateContext(order, stateFactory, eventPublisher);
    }

    /**
     * 生成订单ID
     */
    private String generateOrderId() {
        return "ORD" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    /**
     * 获取所有订单
     */
    public List<Order> getAllOrders() {
        return new ArrayList<>(orderRepository);
    }
}
