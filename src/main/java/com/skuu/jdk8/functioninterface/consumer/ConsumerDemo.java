package com.skuu.jdk8.functioninterface.consumer;

import com.skuu.jdk8.functioninterface.OrderDemo;
import com.skuu.jdk8.functioninterface.OrderOpt;
import com.skuu.jdk8.functioninterface.Req;
import com.skuu.jdk8.functioninterface.Res;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author dcx
 * @description 使用模板
 * @create 2026-02-25 14:41
 **/
public class ConsumerDemo {

    public static void main(String[] args) {
        List<Req> reqs = new ArrayList<>();
        reqs.add(new Req().setName("dd1"));
        reqs.add(new Req().setName("dd2"));
        reqs.add(new Req().setName("dd3"));
        OrderOpt orderOpt = new OrderOpt();
        orderOpt.notifys(reqs, ConsumerDemo::consumer);
        Supplier<OrderDemo> o = () -> new OrderDemo();
        OrderDemo orderDemo = o.get();
    }

    public static void consumer(Req a) {
        String name = a.getName();
        Res res = new Res().setName(name);
        if ("dd2".equals(name)) {
            res.setDesc("经过transed 重名");
        } else {
            res.setDesc("经过transed 不重名");
        }
        System.out.println("消费成功");
    }
}
