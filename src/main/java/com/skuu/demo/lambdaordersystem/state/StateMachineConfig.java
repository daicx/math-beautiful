package com.skuu.demo.lambdaordersystem.state;

import com.skuu.demo.lambdaordersystem.enums.OrderStatusEnum;
import com.skuu.demo.lambdaordersystem.model.Order;
import com.skuu.demo.lambdaordersystem.validator.TransitionRequest;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 状态机配置（lambda 版）：允许的转换表 + 动作处理器表，全部用 Map + Consumer 表达
 */
public class StateMachineConfig {

    /** 允许的转换：from -> Set<to>，用于校验 */
    private final Map<OrderStatusEnum, Set<OrderStatusEnum>> allowedTransitions = new EnumMap<>(OrderStatusEnum.class);
    /** 动作处理器：from -> (actionName -> ActionHandler) */
    private final Map<OrderStatusEnum, Map<String, ActionHandler>> handlers = new EnumMap<>(OrderStatusEnum.class);

    public static StateMachineConfig createDefault() {
        StateMachineConfig c = new StateMachineConfig();

        // PENDING_PAYMENT
        c.allow(OrderStatusEnum.PENDING_PAYMENT, OrderStatusEnum.PAID, OrderStatusEnum.CANCELLED);
        c.on(OrderStatusEnum.PENDING_PAYMENT, "pay", OrderStatusEnum.PAID, ctx -> {
            Order o = ctx.getOrder();
            System.out.println(String.format("订单 %s 支付成功，金额: %.2f 元", o.getOrderId(), o.getAmount()));
            o.addMetadata("paymentTime", System.currentTimeMillis());
            o.addMetadata("paymentMethod", "默认支付方式");
        });
        c.on(OrderStatusEnum.PENDING_PAYMENT, "cancel", OrderStatusEnum.CANCELLED, ctx -> {
            Order o = ctx.getOrder();
            System.out.println(String.format("订单 %s 已取消", o.getOrderId()));
            o.addMetadata("cancelTime", System.currentTimeMillis());
            o.addMetadata("cancelReason", "用户主动取消");
        });

        // PAID
        c.allow(OrderStatusEnum.PAID, OrderStatusEnum.SHIPPED, OrderStatusEnum.REFUNDING);
        c.on(OrderStatusEnum.PAID, "ship", OrderStatusEnum.SHIPPED, ctx -> {
            Order o = ctx.getOrder();
            System.out.println(String.format("订单 %s 开始发货", o.getOrderId()));
            String tracking = "TRK" + System.currentTimeMillis();
            o.addMetadata("shipTime", System.currentTimeMillis());
            o.addMetadata("trackingNumber", tracking);
            System.out.println("物流单号: " + tracking);
        });
        c.on(OrderStatusEnum.PAID, "refund", OrderStatusEnum.REFUNDING, ctx -> {
            Order o = ctx.getOrder();
            System.out.println(String.format("订单 %s 申请退款，金额: %.2f 元", o.getOrderId(), o.getAmount()));
            o.addMetadata("refundApplyTime", System.currentTimeMillis());
        });

        // SHIPPED
        c.allow(OrderStatusEnum.SHIPPED, OrderStatusEnum.DELIVERED);
        c.on(OrderStatusEnum.SHIPPED, "confirm", OrderStatusEnum.DELIVERED, ctx -> {
            Order o = ctx.getOrder();
            System.out.println(String.format("订单 %s 已确认收货", o.getOrderId()));
            o.addMetadata("confirmTime", System.currentTimeMillis());
        });

        // DELIVERED
        c.allow(OrderStatusEnum.DELIVERED, OrderStatusEnum.COMPLETED);
        c.on(OrderStatusEnum.DELIVERED, "complete", OrderStatusEnum.COMPLETED, ctx -> {
            Order o = ctx.getOrder();
            System.out.println(String.format("订单 %s 已完成", o.getOrderId()));
            o.addMetadata("completeTime", System.currentTimeMillis());
        });

        // REFUNDING
        c.allow(OrderStatusEnum.REFUNDING, OrderStatusEnum.REFUNDED);
        c.on(OrderStatusEnum.REFUNDING, "refund", OrderStatusEnum.REFUNDED, ctx -> {
            Order o = ctx.getOrder();
            System.out.println(String.format("订单 %s 退款处理完成，金额: %.2f 元", o.getOrderId(), o.getAmount()));
            o.addMetadata("refundCompleteTime", System.currentTimeMillis());
        });

        // 终态：不 allow 任何 to，不注册任何 action
        c.allow(OrderStatusEnum.COMPLETED);
        c.allow(OrderStatusEnum.CANCELLED);
        c.allow(OrderStatusEnum.REFUNDED);

        // 进入某状态时的侧效（lambda）
        c.onEnter(OrderStatusEnum.COMPLETED, ctx ->
            System.out.println(String.format("订单 %s 已成功完成，感谢您的购买！", ctx.getOrder().getOrderId())));

        return c;
    }

    private final Map<OrderStatusEnum, Consumer<LambdaOrderContext>> onEnterHandlers = new EnumMap<>(OrderStatusEnum.class);

    private void onEnter(OrderStatusEnum status, Consumer<LambdaOrderContext> handler) {
        onEnterHandlers.put(status, handler);
    }

    public Consumer<LambdaOrderContext> getOnEnter(OrderStatusEnum status) {
        return onEnterHandlers.get(status);
    }

    private void allow(OrderStatusEnum from, OrderStatusEnum... toArray) {
        if (toArray.length == 0) {
            allowedTransitions.put(from, Collections.<OrderStatusEnum>emptySet());
        } else {
            Set<OrderStatusEnum> set = new HashSet<>();
            for (OrderStatusEnum e : toArray) set.add(e);
            allowedTransitions.put(from, Collections.unmodifiableSet(set));
        }
    }

    private void on(OrderStatusEnum from, String action, OrderStatusEnum next, Consumer<LambdaOrderContext> before) {
        handlers.computeIfAbsent(from, k -> new HashMap<>()).put(action, new ActionHandler(next, before));
    }

    public Set<OrderStatusEnum> getAllowedTargets(OrderStatusEnum from) {
        return allowedTransitions.getOrDefault(from, Collections.<OrderStatusEnum>emptySet());
    }

    public Map<String, ActionHandler> getHandlers(OrderStatusEnum from) {
        return handlers.get(from);
    }

    /** 用于校验链：当前状态是否允许转到目标状态 */
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
