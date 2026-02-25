package com.skuu.demo.ordersystem.factory;

import com.skuu.demo.ordersystem.enums.OrderStatusEnum;
import com.skuu.demo.ordersystem.state.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dcx
 * @description 状态工厂 - 工厂模式 + Spring Bean
 * 使用 Spring 自动注入所有状态实现类，无需手动注册
 * @create 2025-01-27
 */
@Component
public class StateFactory {

    // 状态对象缓存（状态对象是无状态的，可以复用）
    private final Map<OrderStatusEnum, OrderState> stateCacheMap = new HashMap<>();

    // Spring 自动注入所有 OrderStateBehavior 的实现类
    @Autowired(required = false)
    private List<OrderState> orderStates;

    /**
     * Spring 初始化后自动执行
     * 将所有状态实现类注册到缓存中
     */
    @PostConstruct
    public void initializeStates() {
        if (orderStates != null && !orderStates.isEmpty()) {
            // Spring 环境：使用注入的状态类
            for (OrderState stateBehavior : orderStates) {
                OrderStatusEnum orderStatusEnum = stateBehavior.getStatus();
                if (orderStatusEnum == null) {
                    return;
                }
                if (stateCacheMap.containsKey(orderStatusEnum)) {
                    throw new RuntimeException("重复注册");
                }
                stateCacheMap.put(orderStatusEnum, stateBehavior);
            }
        }

        System.out.println("状态工厂初始化完成，共加载 " + stateCacheMap.size() + " 个状态类");
    }

    /**
     * 创建状态对象
     *
     * @param status 订单状态
     * @return 对应的状态对象
     */
    public OrderState createState(OrderStatusEnum status) {
        OrderState state = stateCacheMap.get(status);
        if (state == null) {
            throw new IllegalArgumentException("未知的订单状态: " + status);
        }
        return state;
    }
}
