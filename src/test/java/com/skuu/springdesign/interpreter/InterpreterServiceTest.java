package com.skuu.springdesign.interpreter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 解释器模式用法：表达式为 Function&lt;Context, Double&gt;，eval(expr, ctx) 求值。
 */
@SpringBootTest
class InterpreterServiceTest {

    @Autowired
    private InterpreterService interpreterService;

    @Test
    void evalExpression() {
        InterpreterService.Context ctx = new InterpreterService.Context();
        ctx.set("x", 10);
        ctx.set("y", 2);
        Function<InterpreterService.Context, Double> expr = c -> c.get("x") + c.get("y");
        double result = interpreterService.eval(expr, ctx);
        assertEquals(12.0, result, 0.01);
    }
}
