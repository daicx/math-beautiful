package com.skuu.springdesign.templatemethod;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 模板方法：订单履约固定流程（校验→步骤→收尾），实物含发货、虚拟不含；步骤可中断，结果统一为 OrderFulfillmentResult。
 */
@SpringBootTest(classes = {
        OrderFulfillmentService.class,
        OrderFulfillmentTemplate.class,
        OrderStepRegistryConfig.class,
        com.skuu.springdesign.templatemethod.step.ReserveInventoryStep.class,
        com.skuu.springdesign.templatemethod.step.ChargePaymentStep.class,
        com.skuu.springdesign.templatemethod.step.ShipStep.class,
        com.skuu.springdesign.templatemethod.step.NotifyStep.class
})
class OrderFulfillmentServiceTest {

    @Autowired
    private OrderFulfillmentService orderFulfillmentService;

    @Test
    void physicalOrderRunsFourSteps() {
        OrderFulfillmentResult result = orderFulfillmentService.fulfill(
                "ORD-P1", OrderData.OrderType.PHYSICAL, new BigDecimal("99.00"));

        assertTrue(result.isSuccess());
        List<String> logs = result.getLogs();
        assertTrue(logs.stream().anyMatch(s -> s.startsWith("ReserveInventory:")));
        assertTrue(logs.stream().anyMatch(s -> s.startsWith("ChargePayment:")));
        assertTrue(logs.stream().anyMatch(s -> s.startsWith("Ship:")));
        assertTrue(logs.stream().anyMatch(s -> s.startsWith("Notify:")));
    }

    @Test
    void digitalOrderSkipsShipStep() {
        OrderFulfillmentResult result = orderFulfillmentService.fulfill(
                "ORD-D1", OrderData.OrderType.DIGITAL, new BigDecimal("10.00"));

        assertTrue(result.isSuccess());
        List<String> logs = result.getLogs();
        assertTrue(logs.stream().anyMatch(s -> s.startsWith("ReserveInventory:")));
        assertTrue(logs.stream().anyMatch(s -> s.startsWith("ChargePayment:")));
        assertFalse(logs.stream().anyMatch(s -> s.startsWith("Ship:")));
        assertTrue(logs.stream().anyMatch(s -> s.startsWith("Notify:")));
    }

    @Test
    void invalidOrderIdFailsValidation() {
        OrderFulfillmentResult result = orderFulfillmentService.fulfill(
                "", OrderData.OrderType.PHYSICAL, new BigDecimal("1"));

        assertFalse(result.isSuccess());
        assertEquals("orderId is required", result.getFailureReason());
    }

    @Test
    void invalidAmountFailsValidation() {
        OrderFulfillmentResult result = orderFulfillmentService.fulfill(
                "ORD-X", OrderData.OrderType.DIGITAL, BigDecimal.ZERO);

        assertFalse(result.isSuccess());
        assertEquals("amount must be positive", result.getFailureReason());
    }

    @Test
    void stepReturnsFalseInterruptsAndReturnsFailure() {
        OrderData data = OrderData.builder()
                .orderId("ORD-FAIL")
                .orderType(OrderData.OrderType.PHYSICAL)
                .amount(new BigDecimal("50"))
                .build();
        OrderStep failingStep = ctx -> {
            ctx.log("FailingStep", "simulated failure");
            ctx.setFailureReason("payment declined");
            return false;
        };
        List<OrderStep> steps = Arrays.asList(
                ctx -> { ctx.log("Before", "ok"); return true; },
                failingStep,
                ctx -> { ctx.log("After", "should not run"); return true; }
        );

        OrderFulfillmentResult result = orderFulfillmentService.fulfill(data, steps);

        assertFalse(result.isSuccess());
        assertEquals("payment declined", result.getFailureReason());
        assertTrue(result.getLogs().stream().anyMatch(s -> s.startsWith("Before:")));
        assertTrue(result.getLogs().stream().anyMatch(s -> s.startsWith("FailingStep:")));
        assertFalse(result.getLogs().stream().anyMatch(s -> s.startsWith("After:")));
    }
}
