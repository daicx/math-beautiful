package com.skuu.demo.ordersystem.state;

import com.skuu.demo.ordersystem.model.OrderStatusEnum;

/**
 * @author dcx
 * @description 抽象订单状态行为 - 模板方法模式
 * 提供默认实现，减少重复代码
 * @create 2025-01-27
 */
public abstract class AbstractOrderStateBehavior implements OrderStateBehavior {
    
    @Override
    public void pay(StateContext context) {
        throw new UnsupportedOperationException(
            String.format("当前状态 %s 不支持支付操作", getStatus().getName())
        );
    }

    @Override
    public void ship(StateContext context) {
        throw new UnsupportedOperationException(
            String.format("当前状态 %s 不支持发货操作", getStatus().getName())
        );
    }

    @Override
    public void confirm(StateContext context) {
        throw new UnsupportedOperationException(
            String.format("当前状态 %s 不支持确认收货操作", getStatus().getName())
        );
    }

    @Override
    public void complete(StateContext context) {
        throw new UnsupportedOperationException(
            String.format("当前状态 %s 不支持完成订单操作", getStatus().getName())
        );
    }

    @Override
    public void cancel(StateContext context) {
        throw new UnsupportedOperationException(
            String.format("当前状态 %s 不支持取消操作", getStatus().getName())
        );
    }

    @Override
    public void refund(StateContext context) {
        throw new UnsupportedOperationException(
            String.format("当前状态 %s 不支持退款操作", getStatus().getName())
        );
    }

    @Override
    public boolean canTransitionTo(OrderStatusEnum targetStatus) {
        // 默认实现：子类可以覆盖
        return false;
    }
}
