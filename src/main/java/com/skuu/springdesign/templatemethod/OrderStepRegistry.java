package com.skuu.springdesign.templatemethod;

import java.util.List;

/**
 * 订单类型 → 步骤链映射，新增类型时只需注册步骤列表，无需改模板代码。
 */
public interface OrderStepRegistry {

    List<OrderStep> getStepsFor(OrderData.OrderType orderType);
}
