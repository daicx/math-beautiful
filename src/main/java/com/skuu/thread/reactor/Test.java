package com.skuu.thread.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author dcx
 * @description
 * @create 2025-12-01 17:52
 **/
public class Test {
    public static void main(String[] args) {
        Mono<String> hello = Mono.just("hello");
        hello.subscribe(System.out::println);
        Flux.range(1, 10).subscribe(System.out::println);
    }
}
