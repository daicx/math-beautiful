package com.skuu.design.command.commands;

import com.skuu.design.command.Command;
import com.skuu.design.command.receivers.Light;

/**
 * @author dcx
 * @description 开灯命令 - 具体命令类
 * @create 2025-01-27
 */
public class LightOnCommand implements Command {
    
    private Light light;
    private int previousBrightness;
    
    public LightOnCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        previousBrightness = light.getBrightness();
        light.on();
    }
    
    @Override
    public void undo() {
        if (previousBrightness == 0) {
            light.off();
        } else {
            light.dim(previousBrightness);
        }
    }
    
    @Override
    public String getDescription() {
        return "打开" + light.getLocation() + "的灯";
    }
}
