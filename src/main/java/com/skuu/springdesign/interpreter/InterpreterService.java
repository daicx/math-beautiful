package com.skuu.springdesign.interpreter;

import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * 解释器：表达式求值用 Function<Context, Double> 表示，组合成树后 apply(context)。
 */
@Service
public class InterpreterService {

    public double eval(Function<Context, Double> expression, Context ctx) {
        return expression.apply(ctx);
    }

    public static class Context {
        private final java.util.Map<String, Double> vars = new java.util.HashMap<>();
        public void set(String name, double value) { vars.put(name, value); }
        public double get(String name) { return vars.getOrDefault(name, 0.0); }
    }
}
