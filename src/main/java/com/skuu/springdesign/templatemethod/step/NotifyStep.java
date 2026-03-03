package com.skuu.springdesign.templatemethod.step;

import com.skuu.springdesign.templatemethod.OrderContext;
import com.skuu.springdesign.templatemethod.OrderStep;
import org.springframework.stereotype.Component;

/**
 * 通知用户步骤。
 */
@Component
public class NotifyStep implements OrderStep {

    @Override
    public boolean execute(OrderContext ctx) {
        ctx.log("Notify", "notified for order " + ctx.getOrderId());
        return true;
    }
}
