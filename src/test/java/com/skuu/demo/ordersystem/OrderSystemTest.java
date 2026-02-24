package com.skuu.demo.ordersystem;

import com.skuu.demo.ordersystem.service.OrderService;
import com.skuu.demo.ordersystem.state.StateContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author dcx
 * @description 订单系统测试类 - Spring Boot 测试
 * 演示完整的订单状态流转过程
 * @create 2025-01-27
 */
@SpringBootTest
public class OrderSystemTest {
    
    @Autowired
    private OrderService orderService;
    
    @Test
    public void testOrderCompleteFlow() {
        System.out.println("========== 订单系统测试 ==========\n");
        
        // 1. 创建订单
        System.out.println("【步骤1】创建订单");
        StateContext context = orderService.createOrder(
            "张三", 
            "USER001", 
            "PROD001", 
            "MacBook Pro", 
            12999.00
        );
        System.out.println("当前状态: " + context.getCurrentStatus().getName() + "\n");
        
        // 2. 支付订单
        System.out.println("【步骤2】支付订单");
        context.pay();
        System.out.println("当前状态: " + context.getCurrentStatus().getName() + "\n");
        
        // 3. 发货
        System.out.println("【步骤3】发货");
        context.ship();
        System.out.println("当前状态: " + context.getCurrentStatus().getName() + "\n");
        
        // 4. 确认收货
        System.out.println("【步骤4】确认收货");
        context.confirm();
        System.out.println("当前状态: " + context.getCurrentStatus().getName() + "\n");
        
        // 5. 完成订单
        System.out.println("【步骤5】完成订单");
        context.complete();
        System.out.println("当前状态: " + context.getCurrentStatus().getName() + "\n");
        
        System.out.println("========== 测试异常情况 ==========\n");
        
        // 测试：已完成订单不能再操作
        try {
            System.out.println("尝试对已完成订单进行支付...");
            context.pay();
        } catch (Exception e) {
            System.out.println("异常捕获: " + e.getMessage() + "\n");
        }
    }
    
    @Test
    public void testRefundFlow() {
        // 测试：退款流程
        System.out.println("========== 测试退款流程 ==========\n");
        StateContext refundContext = orderService.createOrder(
            "李四", 
            "USER002", 
            "PROD002", 
            "iPhone 15", 
            7999.00
        );
        
        System.out.println("【步骤1】创建订单");
        System.out.println("当前状态: " + refundContext.getCurrentStatus().getName() + "\n");
        
        System.out.println("【步骤2】支付订单");
        refundContext.pay();
        System.out.println("当前状态: " + refundContext.getCurrentStatus().getName() + "\n");
        
        System.out.println("【步骤3】申请退款");
        refundContext.refund();
        System.out.println("当前状态: " + refundContext.getCurrentStatus().getName() + "\n");
        
        System.out.println("【步骤4】完成退款");
        refundContext.refund();
        System.out.println("当前状态: " + refundContext.getCurrentStatus().getName() + "\n");
    }
    
    @Test
    public void testCancelFlow() {
        // 测试：取消订单
        System.out.println("========== 测试取消流程 ==========\n");
        StateContext cancelContext = orderService.createOrder(
            "王五", 
            "USER003", 
            "PROD003", 
            "iPad Air", 
            4599.00
        );
        
        System.out.println("【步骤1】创建订单");
        System.out.println("当前状态: " + cancelContext.getCurrentStatus().getName() + "\n");
        
        System.out.println("【步骤2】取消订单");
        cancelContext.cancel();
        System.out.println("当前状态: " + cancelContext.getCurrentStatus().getName() + "\n");
        
        System.out.println("========== 测试完成 ==========");
    }
}
