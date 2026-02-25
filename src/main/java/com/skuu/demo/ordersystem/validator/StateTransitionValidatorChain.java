package com.skuu.demo.ordersystem.validator;

/**
 * @author dcx
 * @description 责任链组装：状态支持 → 终态检查 → 支付金额，体现“链”的串联
 * @create 2025-01-27
 */
public final class StateTransitionValidatorChain {

    private StateTransitionValidatorChain() {}

    /**
     * 构建默认验证链：StateSupport → TerminalState → PaymentAmount
     */
    public static StateTransitionValidator createDefault() {
        AbstractStateTransitionValidator head = new StateSupportValidator();
        AbstractStateTransitionValidator terminal = new TerminalStateValidator();
        AbstractStateTransitionValidator payment = new PaymentAmountValidator();
        head.setNext(terminal);
        terminal.setNext(payment);
        return head;
    }
}
