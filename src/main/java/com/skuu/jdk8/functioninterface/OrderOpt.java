package com.skuu.jdk8.functioninterface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author dcx
 * <p>
 * 在本模板中，定义了一套流程,分别演示了Predicate、Funcation、Consumer函数式编程用法：
 * 1.check    由调用方实现
 * 2.add  由调用方实现
 * 3.pay    本模版实现
 * 3.notifys    由调用方实现
 * @create 2026-02-25 14:53
 **/
public class OrderOpt {

    //检查
    public void check(List<Req> reqs, Predicate<Req> predicate) {
        reqs.forEach(r -> {
            boolean test = predicate.test(r);
            System.out.println("检查成功" + test);
        });
        System.out.println("检查成功---------------");
    }

    //下单
    public List<Res> add(List<Req> reqs, Function<Req, Res> function) {
        List<Res> res = new ArrayList<>();
        reqs.forEach(r -> {
            Res apply = function.apply(r);
            res.add(apply);
            System.out.println("下单成功" + apply);
        });
        System.out.println("下单成功--------------------");
        return res;
    }

    //支付
    public void pay() {
        System.out.println("支付成功");
    }

    //通知
    public void notifys(List<Req> reqs, Consumer<Req> consumer) {
        reqs.forEach(r -> {
            consumer.accept(r);
            System.out.println("通知成功");
        });
        System.out.println("通知成功---------------");
    }
}
