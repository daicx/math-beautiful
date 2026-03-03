package com.skuu.demo.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dcx
 * @description
 * @create 2026-02-28 10:16
 **/
@Service
public class PayService {
    @Autowired
    private PaymentStrategyFactory paymentStrategyFactory;
    @Autowired
    private PaymentRouter paymentRouter;

    public void pay(PayTypeEnum payTypeEnum,long amount) {
        paymentStrategyFactory.getStrategy(payTypeEnum).pay(amount);
    }
    public void smartPay(long amount, boolean isVip) {
        paymentRouter.route(amount, isVip);
    }

}
