package com.skuu.springdesign.proxy;

import org.springframework.stereotype.Service;

/**
 * 代理：函数式包装 Function<String, String>，在调用前后做日志等（或使用 Spring AOP）。
 */
@Service
public class ProxyService {

    private final RealService real;

    public ProxyService(RealService real) {
        this.real = real;
    }

    public String doSomething(String input) {
        return applyWithLog(input);
    }

    private String applyWithLog(String input) {
        System.out.println("[Proxy] before: " + input);
        String result = real.doSomething(input);
        System.out.println("[Proxy] after: " + result);
        return result;
    }
}
