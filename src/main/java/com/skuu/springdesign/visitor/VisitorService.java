package com.skuu.springdesign.visitor;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;

/**
 * 访问者：双分派用 Map<Class<?>, Function<Element, R>> 按类型选择处理函数。
 */
@Service
public class VisitorService {

    public <E, R> R accept(E element, Map<Class<?>, Function<Object, R>> visitors) {
        Function<Object, R> f = visitors.get(element.getClass());
        return f != null ? f.apply(element) : null;
    }
}
