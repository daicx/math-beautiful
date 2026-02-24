package com.skuu.design.command.commands;

import com.skuu.design.command.Command;

/**
 * @author dcx
 * @description 空命令 - 用于初始化，避免空指针
 * @create 2025-01-27
 */
public class NoCommand implements Command {
    
    @Override
    public void execute() {
        // 什么都不做
    }
    
    @Override
    public void undo() {
        // 什么都不做
    }
    
    @Override
    public String getDescription() {
        return "空命令";
    }
}
