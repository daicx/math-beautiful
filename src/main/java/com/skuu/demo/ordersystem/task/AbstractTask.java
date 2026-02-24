package com.skuu.demo.ordersystem.task;

/**
 * @author dcx
 * @description 抽象任务 - 提供默认实现
 * @create 2025-01-27
 */
public abstract class AbstractTask implements Task {
    
    private final String name;
    private final int priority;
    private final int batch;
    
    public AbstractTask(String name, int priority, int batch) {
        this.name = name;
        this.priority = priority;
        this.batch = batch;
    }
    
    public AbstractTask(String name, int priority) {
        this(name, priority, 0);
    }
    
    public AbstractTask(String name) {
        this(name, 0, 0);
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public int getPriority() {
        return priority;
    }
    
    @Override
    public int getBatch() {
        return batch;
    }
}
