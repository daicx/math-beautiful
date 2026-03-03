package com.skuu.demo.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

/**
 * @author dcx
 * @description 支付策略路由
 * @create 2026-02-28 10:06
 **/
@Component
public class PaymentRouter {
    @Autowired
    private PaymentStrategyFactory paymentStrategyFactory;

    public PaymentStrategyService routeByAmount(long amount) {
        return paymentStrategyFactory.getStrategy(amount > 1000 ? PayTypeEnum.ALIPAY : PayTypeEnum.WECHAT);
    }

    public PaymentStrategyService route(long amount, boolean isVip) {
        Predicate<Long> bigAmount = a -> a > 5000L;
        Predicate<Boolean> vipUser = v -> v;
        if (bigAmount.test(amount) && vipUser.test(isVip)) {
            return paymentStrategyFactory.getStrategy(PayTypeEnum.ALIPAY);
        } else {
            return paymentStrategyFactory.getStrategy(PayTypeEnum.WECHAT);
        }
    }
}
