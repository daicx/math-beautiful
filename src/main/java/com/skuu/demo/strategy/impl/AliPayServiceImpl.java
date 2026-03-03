package com.skuu.demo.strategy.impl;

import com.skuu.demo.strategy.PayTypeEnum;
import com.skuu.demo.strategy.PaymentStrategyService;
import org.springframework.stereotype.Service;

/**
 * @author dcx
 * @description
 * @create 2026-02-28 09:56
 **/
@Service
public class AliPayServiceImpl implements PaymentStrategyService {
    @Override
    public PayTypeEnum getPayType() {
        return PayTypeEnum.ALIPAY;
    }

    @Override
    public void pay(long amount) {
        System.out.println("支付宝支付：" + amount);
    }
}
