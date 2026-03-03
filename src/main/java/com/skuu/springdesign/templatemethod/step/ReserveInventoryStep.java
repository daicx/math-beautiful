package com.skuu.springdesign.templatemethod.step;

import com.skuu.springdesign.templatemethod.OrderContext;
import com.skuu.springdesign.templatemethod.OrderStep;
import org.springframework.stereotype.Component;

/**
 * 预留库存步骤（实物/虚拟都可能有）。
 */
@Component
public class ReserveInventoryStep implements OrderStep {

    @Override
    public boolean execute(OrderContext ctx) {
        ctx.log("ReserveInventory", "reserved for order " + ctx.getOrderId());
        return true;
    }
}
