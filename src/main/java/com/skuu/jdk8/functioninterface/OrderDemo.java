package com.skuu.jdk8.functioninterface;

import com.skuu.jdk8.functioninterface.consumer.ConsumerDemo;
import com.skuu.jdk8.functioninterface.function.FunctionDemo;
import com.skuu.jdk8.functioninterface.predicate.PredicateDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dcx
 * @create 2026-02-25 14:53
 **/
public class OrderDemo {
    public static void main(String[] args) {
        List<Req> reqs = new ArrayList<>();
        reqs.add(new Req().setName("dd1"));
        reqs.add(new Req().setName("dd2"));
        reqs.add(new Req().setName("dd3"));
        PredicateDemo predicateDemo = new PredicateDemo();
        OrderContext orderContext = OrderContext.builder()
                .reqs(reqs)
                .orderOpt(new OrderOpt())
                .predicate(predicateDemo::check1)
                .function(FunctionDemo::singleTrans)
                .consumer(ConsumerDemo::consumer)
                .build();
        List<Res> template = orderContext.template();
        System.out.println(template);
    }
}
