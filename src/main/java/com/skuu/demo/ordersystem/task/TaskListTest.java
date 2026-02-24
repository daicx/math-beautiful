package com.skuu.demo.ordersystem.task;

/**
 * @author dcx
 * @description 任务列表测试类
 * 演示如何设置批次和优先级
 * @create 2025-01-27
 */
public class TaskListTest {
    
    public static void main(String[] args) {
        // 创建任务列表
        TaskList taskList = new TaskList();
        
        // 添加任务，设置批次和优先级
        // 批次1：初始化任务（优先级：1-3）
        taskList.addTask(new SimpleTask("初始化数据库", 1, 1));
        taskList.addTask(new SimpleTask("加载配置", 2, 1));
        taskList.addTask(new SimpleTask("初始化缓存", 3, 1));
        
        // 批次2：业务准备任务（优先级：1-2）
        taskList.addTask(new SimpleTask("准备业务数据", 1, 2));
        taskList.addTask(new SimpleTask("验证业务规则", 2, 2));
        
        // 批次3：执行任务（优先级：1-4）
        taskList.addTask(new SimpleTask("处理订单", 1, 3));
        taskList.addTask(new SimpleTask("发送通知", 2, 3));
        taskList.addTask(new SimpleTask("更新库存", 3, 3));
        taskList.addTask(new SimpleTask("记录日志", 4, 3));
        
        // 批次4：清理任务（优先级：1-2）
        taskList.addTask(new SimpleTask("清理临时数据", 1, 4));
        taskList.addTask(new SimpleTask("释放资源", 2, 4));
        
        System.out.println("========== 测试1: 按批次执行 ==========\n");
        taskList.executeByBatch();
        
        System.out.println("\n\n========== 测试2: 按优先级执行（忽略批次） ==========\n");
        taskList.executeByPriority();
        
        System.out.println("\n\n========== 测试3: 只执行批次2 ==========\n");
        taskList.executeBatch(2);
    }
    
    /**
     * 简单任务实现
     */
    static class SimpleTask extends AbstractTask {
        
        public SimpleTask(String name, int priority, int batch) {
            super(name, priority, batch);
        }
        
        @Override
        public void execute() {
            System.out.println("      ✓ " + getName() + " 执行完成");
            try {
                Thread.sleep(100); // 模拟任务执行时间
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
