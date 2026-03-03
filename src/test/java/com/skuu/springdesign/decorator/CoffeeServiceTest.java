package com.skuu.springdesign.decorator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 装饰器模式用法：不可变 Coffee + withMilk/withCaramel，或 Function.andThen 链。
 */
@SpringBootTest
class CoffeeServiceTest {

    @Autowired
    private CoffeeService coffeeService;

    @Test
    void chainedDecoration() {
        Coffee base = Coffee.base("Espresso", 5.0);
        Coffee withMilk = base.withMilk();
        assertEquals("Espresso, Milk", withMilk.getDescription());
        assertEquals(7.0, withMilk.getCost(), 0.01);

        Coffee full = base.withMilk().withCaramel();
        assertTrue(full.getDescription().contains("Caramel"));
        assertEquals(10.0, full.getCost(), 0.01);
    }

    @Test
    void serviceComposedMake() {
        Coffee full = coffeeService.makeWithMilkAndCaramel("espresso");
        assertTrue(full.getDescription().contains("Milk") && full.getDescription().contains("Caramel"));
    }
}
