package com.skuu.springdesign.decorator;

import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * 装饰器模式：用 Function<Coffee, Coffee> 链式叠加（andThen），或 Stream.reduce。
 */
@Service
public class CoffeeService {

    public Coffee make(String base, java.util.List<Function<Coffee, Coffee>> decorators) {
        Coffee baseCoffee = "espresso".equalsIgnoreCase(base)
            ? Coffee.base("Espresso", 5.0)
            : Coffee.base("Americano", 4.0);
        return decorators.stream()
            .reduce(Function.identity(), Function::andThen)
            .apply(baseCoffee);
    }

    public Coffee makeWithMilkAndCaramel(String base) {
        return Stream.<Function<Coffee, Coffee>>of(Coffee::withMilk, Coffee::withCaramel)
            .reduce(Function.identity(), Function::andThen)
            .apply("espresso".equalsIgnoreCase(base) ? Coffee.base("Espresso", 5.0) : Coffee.base("Americano", 4.0));
    }
}
