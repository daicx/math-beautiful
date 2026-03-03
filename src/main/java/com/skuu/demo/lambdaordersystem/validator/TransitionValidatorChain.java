package com.skuu.demo.lambdaordersystem.validator;

import com.skuu.demo.lambdaordersystem.enums.OrderStatusEnum;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * 责任链（lambda 重构版）：Predicate 组合用 Stream.reduce(Predicate::and)
 */
public final class TransitionValidatorChain {

    private static final Set<OrderStatusEnum> TERMINAL = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
        OrderStatusEnum.CANCELLED, OrderStatusEnum.COMPLETED, OrderStatusEnum.REFUNDED)));

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

    public static Predicate<TransitionRequest> paymentAmount() {
        return req -> {
            if (req.getTo() != OrderStatusEnum.PAID || req.getOrder().getAmount() > 0) return true;
            fail("支付金额必须大于0", req);
            return false;
        };
    }

    private static void fail(String reason, TransitionRequest req) {
        System.err.println(String.format("状态转换验证失败: %s -> %s (%s)",
            req.getFrom().getName(), req.getTo().getName(), reason));
    }

    /** 组合多个校验：全部通过才通过（lambda 链） */
    @SafeVarargs
    public static Predicate<TransitionRequest> chain(Predicate<TransitionRequest>... validators) {
        return Stream.of(validators).reduce(Predicate::and).orElse(t -> true);
    }

    public static boolean validate(TransitionRequest req, List<Predicate<TransitionRequest>> chain) {
        return chain.stream().allMatch(p -> p.test(req));
    }
}
