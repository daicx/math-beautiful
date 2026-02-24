package com.skuu.design.combination.proxy;

import com.skuu.design.combination.model.Order;
import com.skuu.design.combination.model.OrderItem;
import com.skuu.design.combination.service.OrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dcx
 * @description 订单服务代理 - 代理模式（添加缓存和日志功能）
 * @create 2025-01-27
 */
public class OrderServiceProxy extends OrderService {
    
    private OrderService target;
    private Map<String, Order> orderCache;
    
    public OrderServiceProxy(OrderService target) {
        this.target = target;
        this.orderCache = new HashMap<>();
    }
    
    /**
     * 代理方法：添加缓存和日志
     */
    @Override
    public Order createAndPayOrder(String userId, List<OrderItem> items, 
                                  String address, String paymentType) {
        // 生成缓存key
        String cacheKey = generateCacheKey(userId, items, address, paymentType);
        
        // 1. 缓存检查
        if (orderCache.containsKey(cacheKey)) {
            System.out.println("\n🔄 [代理-缓存] 从缓存中获取订单");
            return orderCache.get(cacheKey);
        }
        
        // 2. 前置日志
        System.out.println("\n🔄 [代理-日志] 开始创建订单");
        System.out.println("   用户ID: " + userId);
        System.out.println("   支付方式: " + paymentType);
        long startTime = System.currentTimeMillis();
        
        // 3. 调用真实对象
        Order order = target.createAndPayOrder(userId, items, address, paymentType);
        
        // 4. 后置日志
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("\n🔄 [代理-日志] 订单创建完成");
        System.out.println("   耗时: " + duration + "ms");
        
        // 5. 缓存结果
        if (order != null) {
            orderCache.put(cacheKey, order);
            System.out.println("🔄 [代理-缓存] 订单已缓存，缓存大小: " + orderCache.size());
        }
        
        return order;
    }
    
    /**
     * 生成缓存key
     */
    private String generateCacheKey(String userId, List<OrderItem> items, 
                                    String address, String paymentType) {
        return userId + "_" + items.size() + "_" + paymentType;
    }
    
    /**
     * 清空缓存
     */
    public void clearCache() {
        orderCache.clear();
        System.out.println("🔄 [代理-缓存] 缓存已清空");
    }
    
    /**
     * 获取缓存大小
     */
    public int getCacheSize() {
        return orderCache.size();
    }
}

