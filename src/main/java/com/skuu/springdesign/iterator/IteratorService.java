package com.skuu.springdesign.iterator;

import org.springframework.stereotype.Service;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 迭代器：数据源为 Iterable 或 Stream，无需手写 Iterator 实现。
 */
@Service
public class IteratorService {

    public <T> Stream<T> asStream(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
