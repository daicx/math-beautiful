package com.skuu.design.composite;

/**
 * @author dcx
 * @description 文件系统组件抽象接口 - 组合模式的Component
 * @create 2025-01-27
 */
public interface FileSystemComponent {
    
    /**
     * 获取名称
     */
    String getName();
    
    /**
     * 获取大小（字节）
     */
    long getSize();
    
    /**
     * 显示组件信息
     */
    void display(String prefix);
    
    /**
     * 添加子组件（仅容器节点有效）
     */
    default void add(FileSystemComponent component) {
        throw new UnsupportedOperationException("不支持添加操作");
    }
    
    /**
     * 删除子组件（仅容器节点有效）
     */
    default void remove(FileSystemComponent component) {
        throw new UnsupportedOperationException("不支持删除操作");
    }
    
    /**
     * 获取子组件（仅容器节点有效）
     */
    default FileSystemComponent getChild(int index) {
        throw new UnsupportedOperationException("不支持获取子组件操作");
    }
    
    /**
     * 是否为容器节点
     */
    boolean isContainer();
}
