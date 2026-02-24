package com.skuu.design.iterator;

/**
 * @author dcx
 * @description 聚合接口 - 迭代器模式的Aggregate
 * @create 2025-01-27
 */
public interface Aggregate<T> {
    
    /**
     * 创建迭代器
     */
    Iterator<T> createIterator();
    
    /**
     * 添加元素
     */
    void add(T item);
    
    /**
     * 获取元素数量
     */
    int size();
}
