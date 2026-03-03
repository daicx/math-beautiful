package com.skuu.springdesign.templatemethod.step;

import com.skuu.springdesign.templatemethod.OrderContext;
import com.skuu.springdesign.templatemethod.OrderStep;
import org.springframework.stereotype.Component;

/**
 * 发货步骤（仅实物订单需要）。
 */
@Component
public class ShipStep implements OrderStep {

    @Override
    public boolean execute(OrderContext ctx) {
        ctx.log("Ship", "shipped order " + ctx.getOrderId());
        return true;
    }
}
