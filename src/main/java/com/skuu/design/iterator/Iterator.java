package com.skuu.design.iterator;

/**
 * @author dcx
 * @description 迭代器接口 - 迭代器模式的Iterator
 * @create 2025-01-27
 */
public interface Iterator<T> {
    
    /**
     * 是否有下一个元素
     */
    boolean hasNext();
    
    /**
     * 获取下一个元素
     */
    T next();
    
    /**
     * 移除当前元素（可选操作）
     */
    default void remove() {
        throw new UnsupportedOperationException("不支持移除操作");
    }
}
