package com.skuu.demo.ordersystem.service;

import com.skuu.demo.ordersystem.factory.StateFactory;
import com.skuu.demo.ordersystem.model.Order;
import com.skuu.demo.ordersystem.model.OrderStatusEnum;
import com.skuu.demo.ordersystem.observer.impl.InventoryObserver;
import com.skuu.demo.ordersystem.observer.impl.LoggingObserver;
import com.skuu.demo.ordersystem.observer.impl.NotificationObserver;
import com.skuu.demo.ordersystem.state.StateContext;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    // 模拟数据库存储
    private final List<Order> orderRepository;
    
    /**
     * Spring 构造函数注入
     */
    @Autowired
    public OrderService(StateFactory stateFactory) {
        this.stateFactory = stateFactory;
        this.orderRepository = new ArrayList<>();
    }
    
    /**
     * 非 Spring 环境使用（测试用）
     */
    public OrderService(StateFactory stateFactory, boolean testMode) {
        this.stateFactory = stateFactory;
        this.orderRepository = new ArrayList<>();
    }
    
    /**
     * 创建订单
     */
    public StateContext createOrder(String customerName, String userId, 
                                    String productId, String productName, double amount) {
        String orderId = generateOrderId();
        Order order = new Order(orderId, customerName, userId, productId, productName, amount);
        order.setStatus(OrderStatusEnum.PENDING_PAYMENT);
        
        // 创建状态上下文
        StateContext context = new StateContext(order, stateFactory);
        
        // 注册观察者
        registerObservers(context);
        
        // 保存订单
        orderRepository.add(order);
        
        System.out.println(String.format("订单创建成功: %s, 金额: %.2f 元", orderId, amount));
        return context;
    }
    
    /**
     * 根据订单ID加载订单
     */
    public StateContext loadOrder(String orderId) {
        Order order = orderRepository.stream()
            .filter(o -> o.getOrderId().equals(orderId))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("订单不存在: " + orderId));
        
        StateContext context = new StateContext(order, stateFactory);
        registerObservers(context);
        return context;
    }
    
    /**
     * 注册观察者
     */
    private void registerObservers(StateContext context) {
        context.addObserver(new LoggingObserver());
        context.addObserver(new NotificationObserver());
        context.addObserver(new InventoryObserver());
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
