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
        OrderTemplate orderTemplate = new OrderTemplate()
                .setReqs(reqs)
                .setOrderOpt(new OrderOpt())
                .setPredicate(PredicateDemo::check)
                .setFunction(FunctionDemo::singleTrans)
                .setConsumer(ConsumerDemo::consumer);
        List<Res> template = orderTemplate.template();
        System.out.println(template);
    }
}
