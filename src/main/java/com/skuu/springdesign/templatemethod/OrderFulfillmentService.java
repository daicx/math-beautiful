package com.skuu.springdesign.templatemethod;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单履约门面：组装 OrderData，调用模板，返回统一 OrderFulfillmentResult。
 */
@Service
@RequiredArgsConstructor
public class OrderFulfillmentService {

    private final OrderFulfillmentTemplate template;

    public OrderFulfillmentResult fulfill(String orderId, OrderData.OrderType orderType, BigDecimal amount) {
        OrderData data = OrderData.builder()
                .orderId(orderId)
                .orderType(orderType)
                .amount(amount)
                .build();
        return template.execute(data);
    }

    /** 支持传入自定义步骤链（如测试或特殊流程）。 */
    public OrderFulfillmentResult fulfill(OrderData data, List<OrderStep> steps) {
        return template.execute(data, steps);
    }
}
