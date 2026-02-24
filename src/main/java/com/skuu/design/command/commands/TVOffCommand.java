package com.skuu.design.command.commands;

import com.skuu.design.command.Command;
import com.skuu.design.command.receivers.TV;

/**
 * @author dcx
 * @description 关电视命令 - 具体命令类
 * @create 2025-01-27
 */
public class TVOffCommand implements Command {
    
    private TV tv;
    
    public TVOffCommand(TV tv) {
        this.tv = tv;
    }
    
    @Override
    public void execute() {
        tv.off();
    }
    
    @Override
    public void undo() {
        tv.on();
    }
    
    @Override
    public String getDescription() {
        return "关闭电视";
    }
}
