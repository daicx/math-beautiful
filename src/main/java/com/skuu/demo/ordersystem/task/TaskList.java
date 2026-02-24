package com.skuu.demo.ordersystem.task;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author dcx
 * @description 任务列表 - 支持批次和优先级设置
 * @create 2025-01-27
 */
public class TaskList {
    
    private final List<Task> tasks;
    
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    /**
     * 添加任务
     */
    public TaskList addTask(Task task) {
        tasks.add(task);
        return this;
    }
    
    /**
     * 添加多个任务
     */
    public TaskList addTasks(Task... tasks) {
        this.tasks.addAll(Arrays.asList(tasks));
        return this;
    }
    
    /**
     * 按批次执行任务
     * 同一批次内的任务按优先级排序执行
     * 不同批次按批次号顺序执行
     */
    public void executeByBatch() {
        // 按批次分组
        Map<Integer, List<Task>> batchMap = tasks.stream()
            .collect(Collectors.groupingBy(Task::getBatch));
        
        // 按批次号排序
        List<Integer> batchNumbers = new ArrayList<>(batchMap.keySet());
        Collections.sort(batchNumbers);
        
        System.out.println("========== 开始执行任务（按批次） ==========");
        
        for (Integer batchNumber : batchNumbers) {
            List<Task> batchTasks = batchMap.get(batchNumber);
            
            // 批次内按优先级排序
            batchTasks.sort(Comparator.comparingInt(Task::getPriority));
            
            System.out.println("\n【批次 " + batchNumber + "】开始执行，共 " + batchTasks.size() + " 个任务");
            
            for (Task task : batchTasks) {
                System.out.println("  执行任务: " + task.getName() + " (优先级: " + task.getPriority() + ")");
                task.execute();
            }
            
            System.out.println("【批次 " + batchNumber + "】执行完成\n");
        }
        
        System.out.println("========== 所有任务执行完成 ==========");
    }
    
    /**
     * 按优先级执行任务（忽略批次）
     */
    public void executeByPriority() {
        List<Task> sortedTasks = new ArrayList<>(tasks);
        sortedTasks.sort(Comparator.comparingInt(Task::getPriority));
        
        System.out.println("========== 开始执行任务（按优先级） ==========");
        
        for (Task task : sortedTasks) {
            System.out.println("执行任务: " + task.getName() + 
                " (优先级: " + task.getPriority() + ", 批次: " + task.getBatch() + ")");
            task.execute();
        }
        
        System.out.println("========== 所有任务执行完成 ==========");
    }
    
    /**
     * 执行指定批次的任务
     */
    public void executeBatch(int batchNumber) {
        List<Task> batchTasks = tasks.stream()
            .filter(task -> task.getBatch() == batchNumber)
            .sorted(Comparator.comparingInt(Task::getPriority))
            .collect(Collectors.toList());
        
        if (batchTasks.isEmpty()) {
            System.out.println("批次 " + batchNumber + " 没有任务");
            return;
        }
        
        System.out.println("========== 执行批次 " + batchNumber + " ==========");
        
        for (Task task : batchTasks) {
            System.out.println("执行任务: " + task.getName() + " (优先级: " + task.getPriority() + ")");
            task.execute();
        }
        
        System.out.println("========== 批次 " + batchNumber + " 执行完成 ==========");
    }
    
    /**
     * 获取所有任务
     */
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }
    
    /**
     * 清空任务列表
     */
    public void clear() {
        tasks.clear();
    }
}
