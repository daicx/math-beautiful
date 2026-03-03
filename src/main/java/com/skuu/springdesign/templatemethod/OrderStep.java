package com.skuu.springdesign.templatemethod;

/**
 * 订单履约步骤：返回 true 继续下一步，false 中断并视为失败。
 */
@FunctionalInterface
public interface OrderStep {

    /**
     * @return true 成功继续，false 失败并中断后续步骤
     */
    boolean execute(OrderContext ctx);
}
