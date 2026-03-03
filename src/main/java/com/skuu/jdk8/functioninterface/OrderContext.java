package com.skuu.jdk8.functioninterface;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author dcx
 * <p>
 * 运算模板
 * @create 2026-02-25 14:53
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderContext {
    private List<Req> reqs;
    private Predicate<Req> predicate;
    private Consumer<Req> consumer;
    private Function<Req, Res> function;
    private OrderOpt orderOpt;


    /**
     * 定义运算模板
     *
     * @author dcx
     * @date 2026/2/25 15:02
     */
    public List<Res> template() {
        boolean test = predicate.test(reqs.get(0));
        Res apply = function.apply(reqs.get(0));
        consumer.accept(reqs.get(0));
        List<Res> resList = new ArrayList<>();
        orderOpt.check(reqs, predicate);
        orderOpt.add(reqs, function);
        orderOpt.notifys(reqs, consumer);
        return resList;
    }
}
