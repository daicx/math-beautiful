package com.skuu.springdesign.templatemethod;

import com.skuu.springdesign.templatemethod.step.ChargePaymentStep;
import com.skuu.springdesign.templatemethod.step.NotifyStep;
import com.skuu.springdesign.templatemethod.step.ReserveInventoryStep;
import com.skuu.springdesign.templatemethod.step.ShipStep;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.skuu.springdesign.templatemethod.OrderData.OrderType;

/**
 * 类型→步骤映射外置：通过 Map 注册，新增类型只改此处配置。
 */
@Configuration
@RequiredArgsConstructor
public class OrderStepRegistryConfig {

    private final ReserveInventoryStep reserve;
    private final ChargePaymentStep charge;
    private final ShipStep ship;
    private final NotifyStep notify;

    @Bean
    public OrderStepRegistry orderStepRegistry() {
        Map<OrderType, List<OrderStep>> map = new HashMap<>();
        map.put(OrderType.PHYSICAL, Arrays.asList(reserve, charge, ship, notify));
        map.put(OrderType.DIGITAL, Arrays.asList(reserve, charge, notify));
        final Map<OrderType, List<OrderStep>> registry = Collections.unmodifiableMap(map);
        return new OrderStepRegistry() {
            @Override
            public List<OrderStep> getStepsFor(OrderType orderType) {
                if (orderType == null) return Collections.emptyList();
                List<OrderStep> steps = registry.get(orderType);
                return steps != null ? steps : Collections.<OrderStep>emptyList();
            }
        };
    }
}
