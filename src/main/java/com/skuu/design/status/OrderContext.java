package com.skuu.design.status;

/**
 * @author dcx
 * @description 订单上下文,
 * 状态模式：允许对象在内部状态改变时改变其行为，让对象看起来像是修改了它的类。
 * @create 2025-01-27
 */
public class OrderContext {
    private OrderState currentState;
    private Order order; // 使用Order类封装订单信息

    // 构造函数 - 使用Order对象
    public OrderContext(Order order) {
        this.order = order;
        // 根据订单状态初始化当前状态对象
        this.currentState = OrderStateFactory.createState(order.getStatus());
    }

    // 状态转换方法
    public void changeState(OrderStatusEnum newStatus) {
        System.out.println("订单 " + order.getOrderId() + " 状态从 " + 
            order.getStatus().getName() + " 转换为 " + newStatus.getName());
        
        // 更新订单状态
        order.updateStatus(newStatus);
        this.currentState = OrderStateFactory.createState(newStatus);
        
        // 保存到数据库
        saveToDatabase();
    }

    // 委托给当前状态处理的方法
    public void pay() {
        currentState.pay(this);
    }

    public void deliver() {
        currentState.deliver(this);
    }

    public void cancel() {
        currentState.cancel(this);
    }

    public void complete() {
        currentState.complete(this);
    }

    // 模拟保存到数据库
    private void saveToDatabase() {
        System.out.println("保存订单状态到数据库: " + order.getOrderId() + " -> " + order.getStatus().getName());
        // 这里应该是实际的数据库保存逻辑
        // orderRepository.updateStatus(order.getOrderId(), order.getStatus().getCode(), order.getUpdatedAt());
    }

    // Getter方法
    public OrderState getCurrentState() { return currentState; }
    public Order getOrder() { return order; }
    
    // 便捷方法，委托给Order对象
    public String getOrderId() { return order.getOrderId(); }
    public String getCustomerName() { return order.getCustomerName(); }
    public double getAmount() { return order.getAmount(); }
    public OrderStatusEnum getStatus() { return order.getStatus(); }
    public java.util.Map<String, Object> getOrderData() { return order.getOrderData(); }
    public long getCreatedAt() { return order.getCreatedAt(); }
    public long getUpdatedAt() { return order.getUpdatedAt(); }

    @Override
    public String toString() {
        return "OrderContext{" +
                "order=" + order +
                ", currentState=" + currentState.getClass().getSimpleName() +
                '}';
    }
}