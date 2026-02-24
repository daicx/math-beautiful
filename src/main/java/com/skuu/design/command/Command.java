package com.skuu.design.command;

/**
 * @author dcx
 * @description 命令接口 - 命令模式的Command
 * @create 2025-01-27
 */
public interface Command {
    
    /**
     * 执行命令
     */
    void execute();
    
    /**
     * 撤销命令
     */
    void undo();
    
    /**
     * 获取命令描述
     */
    String getDescription();
}
