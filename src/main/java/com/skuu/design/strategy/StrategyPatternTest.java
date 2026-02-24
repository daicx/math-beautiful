package com.skuu.design.strategy;

import com.skuu.design.strategy.payment.AliPayPayment;
import com.skuu.design.strategy.payment.CreditCardPayment;
import com.skuu.design.strategy.payment.WeChatPayment;

/**
 * @author dcx
 * @description 策略模式测试类
 * 演示如何在不同策略之间动态切换
 * @create 2025-01-27
 */
public class StrategyPatternTest {
    
    public static void main(String[] args) {
        // 创建购物车
        ShoppingCart cart = new ShoppingCart();
        
        // 添加商品
        cart.addItem("笔记本电脑", 5999.0);
        cart.addItem("无线鼠标", 99.0);
        cart.addItem("机械键盘", 399.0);
        
        System.out.println("购物车总金额: " + cart.getTotalAmount() + " 元\n");
        
        // ==========================================
        // 策略1: 使用信用卡支付
        // ==========================================
        System.out.println("【策略1】信用卡支付");
        PaymentStrategy creditCardStrategy = new CreditCardPayment("1234567890123456", "张三");
        cart.setPaymentStrategy(creditCardStrategy);
        cart.checkout();
        
        // ==========================================
        // 策略2: 使用支付宝支付
        // ==========================================
        System.out.println("【策略2】支付宝支付");
        PaymentStrategy aliPayStrategy = new AliPayPayment("138****8888");
        cart.setPaymentStrategy(aliPayStrategy);
        cart.checkout();
        
        // ==========================================
        // 策略3: 使用微信支付
        // ==========================================
        System.out.println("【策略3】微信支付");
        PaymentStrategy weChatStrategy = new WeChatPayment("wx_id_123456");
        cart.setPaymentStrategy(weChatStrategy);
        cart.checkout();
        
        // ==========================================
        // 策略模式优势演示：
        // 1. 开闭原则：可以轻松添加新的支付方式（如PayPal），无需修改现有代码
        // 2. 运行时切换：可以在运行时动态改变策略
        // 3. 消除条件语句：不需要用 if-else 来选择不同的支付方式
        // ==========================================
    }
}

