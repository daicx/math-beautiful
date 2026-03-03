package com.skuu.demo.lambdaordersystem.state;

import com.skuu.demo.lambdaordersystem.enums.OrderActionEnum;
import com.skuu.demo.lambdaordersystem.enums.OrderStatusEnum;
import com.skuu.demo.lambdaordersystem.model.Order;
import com.skuu.demo.lambdaordersystem.validator.TransitionRequest;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 状态机配置（lambda 重构版）：转换表 + 动作枚举 + 侧效用方法引用/lambda 注册
 */
public class StateMachineConfig {

    private final Map<OrderStatusEnum, Set<OrderStatusEnum>> allowedTransitions = new EnumMap<>(OrderStatusEnum.class);
    private final Map<OrderStatusEnum, Map<OrderActionEnum, ActionHandler>> handlers = new EnumMap<>(OrderStatusEnum.class);
    private final Map<OrderStatusEnum, Consumer<LambdaOrderContext>> onEnterHandlers = new EnumMap<>(OrderStatusEnum.class);

    public static StateMachineConfig createDefault() {
        StateMachineConfig c = new StateMachineConfig();

        c.allow(OrderStatusEnum.PENDING_PAYMENT, OrderStatusEnum.PAID, OrderStatusEnum.CANCELLED);
        c.on(OrderStatusEnum.PENDING_PAYMENT, OrderActionEnum.PAY, OrderStatusEnum.PAID, StateMachineConfig::onPay);
        c.on(OrderStatusEnum.PENDING_PAYMENT, OrderActionEnum.CANCEL, OrderStatusEnum.CANCELLED, StateMachineConfig::onCancel);

        c.allow(OrderStatusEnum.PAID, OrderStatusEnum.SHIPPED, OrderStatusEnum.REFUNDING);
        c.on(OrderStatusEnum.PAID, OrderActionEnum.SHIP, OrderStatusEnum.SHIPPED, StateMachineConfig::onShip);
        c.on(OrderStatusEnum.PAID, OrderActionEnum.REFUND, OrderStatusEnum.REFUNDING, StateMachineConfig::onRefundApply);

        c.allow(OrderStatusEnum.SHIPPED, OrderStatusEnum.DELIVERED);
        c.on(OrderStatusEnum.SHIPPED, OrderActionEnum.CONFIRM, OrderStatusEnum.DELIVERED, StateMachineConfig::onConfirm);

        c.allow(OrderStatusEnum.DELIVERED, OrderStatusEnum.COMPLETED);
        c.on(OrderStatusEnum.DELIVERED, OrderActionEnum.COMPLETE, OrderStatusEnum.COMPLETED, StateMachineConfig::onComplete);

        c.allow(OrderStatusEnum.REFUNDING, OrderStatusEnum.REFUNDED);
        c.on(OrderStatusEnum.REFUNDING, OrderActionEnum.REFUND, OrderStatusEnum.REFUNDED, StateMachineConfig::onRefundComplete);

        c.allow(OrderStatusEnum.COMPLETED);
        c.allow(OrderStatusEnum.CANCELLED);
        c.allow(OrderStatusEnum.REFUNDED);

        c.onEnter(OrderStatusEnum.COMPLETED, ctx ->
            System.out.println(String.format("订单 %s 已成功完成，感谢您的购买！", ctx.getOrder().getOrderId())));

        return c;
    }

    // ---------- 动作侧效：静态方法，供方法引用 ----------
    private static void onPay(LambdaOrderContext ctx) {
        Order o = ctx.getOrder();
        System.out.println(String.format("订单 %s 支付成功，金额: %.2f 元", o.getOrderId(), o.getAmount()));
        o.addMetadata("paymentTime", System.currentTimeMillis());
        o.addMetadata("paymentMethod", "默认支付方式");
    }

    private static void onCancel(LambdaOrderContext ctx) {
        Order o = ctx.getOrder();
        System.out.println(String.format("订单 %s 已取消", o.getOrderId()));
        o.addMetadata("cancelTime", System.currentTimeMillis());
        o.addMetadata("cancelReason", "用户主动取消");
    }

    private static void onShip(LambdaOrderContext ctx) {
        Order o = ctx.getOrder();
        System.out.println(String.format("订单 %s 开始发货", o.getOrderId()));
        String tracking = "TRK" + System.currentTimeMillis();
        o.addMetadata("shipTime", System.currentTimeMillis());
        o.addMetadata("trackingNumber", tracking);
        System.out.println("物流单号: " + tracking);
    }

    private static void onRefundApply(LambdaOrderContext ctx) {
        Order o = ctx.getOrder();
        System.out.println(String.format("订单 %s 申请退款，金额: %.2f 元", o.getOrderId(), o.getAmount()));
        o.addMetadata("refundApplyTime", System.currentTimeMillis());
    }

    private static void onConfirm(LambdaOrderContext ctx) {
        Order o = ctx.getOrder();
        System.out.println(String.format("订单 %s 已确认收货", o.getOrderId()));
        o.addMetadata("confirmTime", System.currentTimeMillis());
    }

    private static void onComplete(LambdaOrderContext ctx) {
        Order o = ctx.getOrder();
        System.out.println(String.format("订单 %s 已完成", o.getOrderId()));
        o.addMetadata("completeTime", System.currentTimeMillis());
    }

    private static void onRefundComplete(LambdaOrderContext ctx) {
        Order o = ctx.getOrder();
        System.out.println(String.format("订单 %s 退款处理完成，金额: %.2f 元", o.getOrderId(), o.getAmount()));
        o.addMetadata("refundCompleteTime", System.currentTimeMillis());
    }

    private void onEnter(OrderStatusEnum status, Consumer<LambdaOrderContext> handler) {
        onEnterHandlers.put(status, handler);
    }

    private void allow(OrderStatusEnum from, OrderStatusEnum... toArray) {
        allowedTransitions.put(from, toArray.length == 0
            ? Collections.<OrderStatusEnum>emptySet()
            : Collections.unmodifiableSet(new HashSet<>(Arrays.asList(toArray))));
    }

    private void on(OrderStatusEnum from, OrderActionEnum action, OrderStatusEnum next, Consumer<LambdaOrderContext> before) {
        handlers.computeIfAbsent(from, k -> new EnumMap<>(OrderActionEnum.class))
            .put(action, new ActionHandler(next, before != null ? before : ctx -> {}));
    }

    public Consumer<LambdaOrderContext> getOnEnter(OrderStatusEnum status) {
        return onEnterHandlers.get(status);
    }

    public Set<OrderStatusEnum> getAllowedTargets(OrderStatusEnum from) {
        return allowedTransitions.getOrDefault(from, Collections.<OrderStatusEnum>emptySet());
    }

    public Map<OrderActionEnum, ActionHandler> getHandlers(OrderStatusEnum from) {
        return handlers.get(from);
    }

    public Predicate<TransitionRequest> stateSupportPredicate() {
        return req -> {
            Set<OrderStatusEnum> allowed = allowedTransitions.get(req.getFrom());
            if (allowed != null && allowed.contains(req.getTo())) return true;
            System.err.println(String.format("状态转换验证失败: %s -> %s (状态不支持)",
                req.getFrom().getName(), req.getTo().getName()));
            return false;
        };
    }
}
