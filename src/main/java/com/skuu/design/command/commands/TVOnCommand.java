package com.skuu.design.command.commands;

import com.skuu.design.command.Command;
import com.skuu.design.command.receivers.TV;

/**
 * @author dcx
 * @description 开电视命令 - 具体命令类
 * @create 2025-01-27
 */
public class TVOnCommand implements Command {
    
    private TV tv;
    
    public TVOnCommand(TV tv) {
        this.tv = tv;
    }
    
    @Override
    public void execute() {
        tv.on();
    }
    
    @Override
    public void undo() {
        tv.off();
    }
    
    @Override
    public String getDescription() {
        return "打开电视";
    }
}
