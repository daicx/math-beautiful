package com.skuu.demo.ordersystem.task;

/**
 * @author dcx
 * @description 任务接口
 * @create 2025-01-27
 */
public interface Task {
    /**
     * 执行任务
     */
    void execute();
    
    /**
     * 获取任务名称
     */
    String getName();
    
    /**
     * 获取任务优先级（数字越小优先级越高）
     */
    default int getPriority() {
        return 0;
    }
    
    /**
     * 获取任务批次（同一批次的任务可以并行执行）
     */
    default int getBatch() {
        return 0;
    }
}
