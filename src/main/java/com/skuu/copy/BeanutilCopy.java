package com.skuu.copy;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author dcx
 * @description 对象 范型擦除
 * 1.避免直接拷贝泛型容器字段（如 List<T>、Map<K,V>）。
 * 2.对泛型字段单独处理：先拷贝外层对象，再手动/工具转换内部元素。
 * 3.优先使用 MapStruct：尤其在 DTO/Entity 转换频繁的项目中。
 * @create 2025-10-31 14:29
 **/
@Slf4j
public class BeanutilCopy {

    @Data
    public static class A {
        private List<Integer> list;
    }

    @Data
    public static class B {
        private List<String> list;
    }

    public static void main(String[] args) throws JsonProcessingException {
        //准备a数据
        List<Integer> as = Collections.singletonList(111);
        A a = new A();
        a.setList(as);
        //copy到b数据
        B b = new B();
        //泛型容器字段spring2.2.5版本能够copy过去，但是无法取出来。spring2.7.15版本无法copy过去。
        BeanUtils.copyProperties(a, b);
        //验证
        System.out.println(a);
        System.out.println(b);
        System.out.println(b.getList());
        //因为范型擦除，对象String指向了int类型，导致java.lang.Integer cannot be cast to java.lang.String
        for (String bi : b.getList()) {
            System.out.println(bi);
        }
    }
}
