package com.skuu.design.command;

import com.skuu.design.command.commands.NoCommand;

import java.util.Stack;

/**
 * @author dcx
 * @description 遥控器类 - 命令模式的Invoker（调用者）
 * @create 2025-01-27
 */
public class RemoteControl {
    
    /**
     * 遥控器有7个插槽，每个插槽可以设置一个命令
     */
    private Command[] onCommands;
    private Command[] offCommands;
    
    /**
     * 撤销命令栈
     */
    private Stack<Command> undoStack;
    
    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];
        undoStack = new Stack<>();
        
        // 初始化为空命令，避免空指针
        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }
    
    /**
     * 设置命令
     */
    public void setCommand(int slot, Command onCommand, Command offCommand) {
        if (slot >= 0 && slot < 7) {
            onCommands[slot] = onCommand;
            offCommands[slot] = offCommand;
        }
    }
    
    /**
     * 按下ON按钮
     */
    public void onButtonWasPressed(int slot) {
        if (slot >= 0 && slot < 7) {
            System.out.println("\n🔘 按下ON按钮 [插槽" + slot + "]");
            onCommands[slot].execute();
            undoStack.push(onCommands[slot]);
        }
    }
    
    /**
     * 按下OFF按钮
     */
    public void offButtonWasPressed(int slot) {
        if (slot >= 0 && slot < 7) {
            System.out.println("\n🔘 按下OFF按钮 [插槽" + slot + "]");
            offCommands[slot].execute();
            undoStack.push(offCommands[slot]);
        }
    }
    
    /**
     * 按下撤销按钮
     */
    public void undoButtonWasPressed() {
        if (!undoStack.isEmpty()) {
            System.out.println("\n↩️ 按下撤销按钮");
            Command command = undoStack.pop();
            command.undo();
        } else {
            System.out.println("\n❌ 没有可撤销的命令");
        }
    }
    
    /**
     * 显示遥控器状态
     */
    public void showStatus() {
        System.out.println("\n📱 ===== 遥控器状态 =====");
        for (int i = 0; i < 7; i++) {
            System.out.println("[插槽" + i + "] ON: " + onCommands[i].getDescription() + 
                             "  |  OFF: " + offCommands[i].getDescription());
        }
        System.out.println("========================");
    }
}
