package com.skuu.springdesign.proxy;

import org.springframework.stereotype.Service;

@Service
public class RealService {
    public String doSomething(String input) {
        return "real:" + input;
    }
}
