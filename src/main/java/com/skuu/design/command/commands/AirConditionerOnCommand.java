package com.skuu.design.command.commands;

import com.skuu.design.command.Command;
import com.skuu.design.command.receivers.AirConditioner;

/**
 * @author dcx
 * @description 开空调命令 - 具体命令类
 * @create 2025-01-27
 */
public class AirConditionerOnCommand implements Command {
    
    private AirConditioner ac;
    
    public AirConditionerOnCommand(AirConditioner ac) {
        this.ac = ac;
    }
    
    @Override
    public void execute() {
        ac.on();
        ac.setTemperature(24);
    }
    
    @Override
    public void undo() {
        ac.off();
    }
    
    @Override
    public String getDescription() {
        return "打开空调并设置温度";
    }
}
