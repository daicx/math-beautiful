package com.skuu.design.command.commands;

import com.skuu.design.command.Command;

/**
 * @author dcx
 * @description 宏命令 - 批量执行多个命令
 * @create 2025-01-27
 */
public class MacroCommand implements Command {
    
    private Command[] commands;
    private String description;
    
    public MacroCommand(Command[] commands, String description) {
        this.commands = commands;
        this.description = description;
    }
    
    @Override
    public void execute() {
        System.out.println("🎯 执行宏命令: " + description);
        for (Command command : commands) {
            command.execute();
        }
    }
    
    @Override
    public void undo() {
        System.out.println("↩️ 撤销宏命令: " + description);
        // 倒序撤销
        for (int i = commands.length - 1; i >= 0; i--) {
            commands[i].undo();
        }
    }
    
    @Override
    public String getDescription() {
        return description;
    }
}
