package com.skuu.design.strategy;

/**
 * @author dcx
 * @description 购物车 - 策略模式中的上下文类(Context)
 * 持有策略接口的引用，可以在运行时更换策略
 * @create 2025-01-27
 */
public class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    private double totalAmount = 0.0;

    /**
     * 设置支付策略
     * @param paymentStrategy 支付策略
     */
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    /**
     * 添加商品
     * @param itemName 商品名称
     * @param price 价格
     */
    public void addItem(String itemName, double price) {
        totalAmount += price;
        System.out.println("添加商品: " + itemName + ", 价格: " + price + " 元");
    }

    /**
     * 执行支付
     * 委托给当前设置的支付策略
     */
    public void checkout() {
        if (paymentStrategy == null) {
            System.out.println("错误: 未设置支付方式！");
            return;
        }
        
        System.out.println("\n=== 结账 ===");
        System.out.println("总金额: " + totalAmount + " 元");
        paymentStrategy.pay(totalAmount);
        System.out.println("================\n");
    }

    /**
     * 获取总金额
     */
    public double getTotalAmount() {
        return totalAmount;
    }
}

