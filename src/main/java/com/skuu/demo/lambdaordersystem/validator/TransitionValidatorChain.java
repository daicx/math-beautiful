package com.skuu.demo.lambdaordersystem.validator;

import com.skuu.demo.lambdaordersystem.enums.OrderStatusEnum;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * 责任链用 JDK8 Predicate 表达：多个校验条件 and 在一起
 */
public final class TransitionValidatorChain {

    private static final Set<OrderStatusEnum> TERMINAL = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
        OrderStatusEnum.CANCELLED, OrderStatusEnum.COMPLETED, OrderStatusEnum.REFUNDED)));

    /** 当前状态是否支持转到目标状态（由 StateMachineConfig 提供） */
    public static Predicate<TransitionRequest> stateSupport(Predicate<TransitionRequest> allowed) {
        return allowed;
    }

    /** 终态不可再转出 */
    public static Predicate<TransitionRequest> terminalState() {
        return req -> {
            if (TERMINAL.contains(req.getFrom())) {
                System.err.println(String.format("状态转换验证失败: %s -> %s (终态不可再转换)",
                    req.getFrom().getName(), req.getTo().getName()));
                return false;
            }
            return true;
        };
    }

    /** 转为已支付时金额必须 > 0 */
    public static Predicate<TransitionRequest> paymentAmount() {
        return req -> {
            if (req.getTo() != OrderStatusEnum.PAID) return true;
            if (req.getOrder().getAmount() > 0) return true;
            System.err.println(String.format("状态转换验证失败: %s -> %s (支付金额必须大于0)",
                req.getFrom().getName(), req.getTo().getName()));
            return false;
        };
    }

    /** 组合多个校验：全部通过才通过 */
    @SafeVarargs
    public static Predicate<TransitionRequest> chain(Predicate<TransitionRequest>... validators) {
        return Arrays.stream(validators).reduce(Predicate::and).orElse(t -> true);
    }

    public static boolean validate(TransitionRequest req, List<Predicate<TransitionRequest>> chain) {
        return chain.stream().allMatch(p -> p.test(req));
    }
}
