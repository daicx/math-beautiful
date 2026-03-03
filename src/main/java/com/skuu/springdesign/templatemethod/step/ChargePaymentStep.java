package com.skuu.springdesign.templatemethod.step;

import com.skuu.springdesign.templatemethod.OrderContext;
import com.skuu.springdesign.templatemethod.OrderStep;
import org.springframework.stereotype.Component;

/**
 * 扣款步骤。
 */
@Component
public class ChargePaymentStep implements OrderStep {

    @Override
    public boolean execute(OrderContext ctx) {
        ctx.log("ChargePayment", "charged " + ctx.getAmount() + " for " + ctx.getOrderId());
        return true;
    }
}
