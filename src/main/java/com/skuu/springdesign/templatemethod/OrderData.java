package com.skuu.springdesign.templatemethod;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

/**
 * 订单数据（纯数据，与步骤链解耦）。
 */
@Value
@Builder
public class OrderData {

    String orderId;
    OrderType orderType;
    BigDecimal amount;

    public enum OrderType { PHYSICAL, DIGITAL }
}
