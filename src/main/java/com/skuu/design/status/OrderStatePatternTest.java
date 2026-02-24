package com.skuu.design.status;

import java.util.HashMap;

/**
 * @author dcx
 * @description 使用Order类的状态模式测试
 * @create 2025-01-27
 */
public class OrderStatePatternTest {

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 使用Order类的状态模式演示 ===\n");

        OrderService orderService = new OrderService();

        // 1. 从数据库加载一个待支付的订单
        System.out.println("=== 加载待支付订单 ===");
        OrderContext order = orderService.loadOrderFromDatabase("ORD001");
        System.out.println("加载的订单：" + order);
        System.out.println("订单详情：" + order.getOrder());
        System.out.println();

        // 2. 继续处理这个订单
        System.out.println("=== 继续处理订单 ===");
        order.pay();        // 支付
        order.deliver();    // 发货
        order.complete();   // 完成
        System.out.println();

        // 3. 创建一个新订单
        System.out.println("=== 创建新订单 ===");
        OrderContext newOrder = orderService.createOrder(
                "李四",
                "USER002",
                "PROD002",
                "MacBook Pro",
                12999.99
        );
        System.out.println("新订单：" + newOrder);
        System.out.println("订单详情：" + newOrder.getOrder());

        // 处理新订单
        newOrder.pay();
        newOrder.deliver();
        newOrder.complete();
        System.out.println();

        // 4. 手动创建一个已支付的订单
        System.out.println("=== 手动创建已支付订单 ===");
        Order paidOrder = new Order(
                "ORD003",
                "王五",
                "USER003",
                "PROD003",
                "iPad Air",
                4999.99,
                OrderStatusEnum.PAID
        );
        paidOrder.addOrderData("paymentMethod", "微信支付");
        paidOrder.addOrderData("address", "上海市浦东新区");
        paidOrder.setCreatedAt(System.currentTimeMillis() - 7200000);  // 2小时前创建
        paidOrder.setUpdatedAt(System.currentTimeMillis() - 1800000);   // 30分钟前更新

        OrderContext paidOrderContext = new OrderContext(paidOrder);
        System.out.println("已支付订单：" + paidOrderContext);

        // 继续处理
        paidOrderContext.deliver();    // 发货
        paidOrderContext.complete();   // 完成
        System.out.println();

        // 5. 批量处理待支付订单
        System.out.println("=== 批量处理待支付订单 ===");
        java.util.List<OrderContext> pendingOrders = orderService.loadOrdersByStatus(OrderStatusEnum.PENDING_PAYMENT);
        for (OrderContext pendingOrder : pendingOrders) {
            System.out.println("处理订单：" + pendingOrder.getOrderId() +
                    " - " + pendingOrder.getOrder().getProductName());
            pendingOrder.pay();
            System.out.println();
        }

        // 6. 测试状态工厂
        System.out.println("=== 测试状态工厂 ===");
        for (OrderStatusEnum status : OrderStatusEnum.values()) {
            OrderState state = OrderStateFactory.createState(status);
            System.out.println("状态: " + status.getName() + " -> 状态对象: " + state.getClass().getSimpleName());
        }
        System.out.println();
    }
}
