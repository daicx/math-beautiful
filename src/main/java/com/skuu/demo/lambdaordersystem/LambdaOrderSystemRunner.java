package com.skuu.demo.lambdaordersystem;

import com.skuu.demo.lambdaordersystem.service.LambdaOrderService;
import com.skuu.demo.lambdaordersystem.state.LambdaOrderContext;

/**
 * lambda 版订单系统入口：无 Spring、无状态子类，纯 JDK8 lambda + Map 驱动
 */
public class LambdaOrderSystemRunner {

    public static void main(String[] args) {
        LambdaOrderService service = new LambdaOrderService();
        LambdaOrderContext ctx = service.createOrder("张三", "USER001", "PROD001", "MacBook Pro", 12999.00);

        ctx.pay();
        ctx.ship();
        ctx.confirm();
        ctx.complete();
    }
}
