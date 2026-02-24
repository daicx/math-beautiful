package com.skuu.design.command;

import com.skuu.design.command.commands.*;
import com.skuu.design.command.receivers.*;

/**
 * @author dcx
 * @description 命令模式测试类
 * @create 2025-01-27
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("=== 命令模式 - 智能家居遥控器示例 ===\n");

        // ========== 创建接收者（家电设备） ==========
        Light livingRoomLight = new Light("客厅");
        Light bedroomLight = new Light("卧室");
        TV livingRoomTV = new TV("客厅");
        AirConditioner livingRoomAC = new AirConditioner("客厅");

        // ========== 创建命令对象 ==========
        // 灯光命令
        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
        LightOnCommand bedroomLightOn = new LightOnCommand(bedroomLight);
        LightOffCommand bedroomLightOff = new LightOffCommand(bedroomLight);

        // 电视命令
        TVOnCommand tvOn = new TVOnCommand(livingRoomTV);
        TVOffCommand tvOff = new TVOffCommand(livingRoomTV);

        // 空调命令
        AirConditionerOnCommand acOn = new AirConditionerOnCommand(livingRoomAC);

        // ========== 创建遥控器（调用者） ==========
        RemoteControl remote = new RemoteControl();

        // 设置命令到遥控器插槽
        remote.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remote.setCommand(1, bedroomLightOn, bedroomLightOff);
        remote.setCommand(2, tvOn, tvOff);
        remote.setCommand(3, acOn, acOn);  // 空调使用同一个命令

        // 显示遥控器状态
        remote.showStatus();

        // ========== 场景1：基本命令执行 ==========
        System.out.println("\n【场景1：基本命令执行】");
        
        // 打开客厅灯
        remote.onButtonWasPressed(0);
        
        // 关闭客厅灯
        remote.offButtonWasPressed(0);
        
        // 打开卧室灯
        remote.onButtonWasPressed(1);
        
        // 打开电视
        remote.onButtonWasPressed(2);

        // ========== 场景2：撤销命令 ==========
        System.out.println("\n\n【场景2：撤销命令】");
        
        // 打开空调
        remote.onButtonWasPressed(3);
        
        // 撤销（关闭空调）
        remote.undoButtonWasPressed();
        
        // 再次撤销（打开电视被撤销，即关闭电视）
        remote.undoButtonWasPressed();

        // ========== 场景3：宏命令（批量执行） ==========
        System.out.println("\n\n【场景3：宏命令 - 回家模式】");
        
        // 创建"回家模式"宏命令：打开客厅灯、打开电视、打开空调
        Command[] partyOnCommands = {
            livingRoomLightOn,
            tvOn,
            acOn
        };
        MacroCommand partyOnMacro = new MacroCommand(partyOnCommands, "回家模式");
        
        // 创建"离家模式"宏命令：关闭所有设备
        Command[] partyOffCommands = {
            livingRoomLightOff,
            bedroomLightOff,
            tvOff
        };
        MacroCommand partyOffMacro = new MacroCommand(partyOffCommands, "离家模式");
        
        // 将宏命令设置到遥控器
        remote.setCommand(6, partyOnMacro, partyOffMacro);
        
        // 执行"回家模式"
        remote.onButtonWasPressed(6);
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // 执行"离家模式"
        System.out.println();
        remote.offButtonWasPressed(6);
        
        // 撤销"离家模式"
        System.out.println();
        remote.undoButtonWasPressed();

        // ========== 总结 ==========
        System.out.println("\n\n=== 命令模式说明 ===");
        System.out.println("1. 命令接口: Command - 定义执行和撤销方法");
        System.out.println("2. 具体命令: LightOnCommand等 - 封装请求为对象");
        System.out.println("3. 接收者: Light、TV等 - 真正执行操作的对象");
        System.out.println("4. 调用者: RemoteControl - 持有命令并触发执行");
        System.out.println("5. 客户端: 创建命令对象并设置接收者");

        System.out.println("\n=== 命令模式优势 ===");
        System.out.println("✅ 解耦: 调用者与接收者解耦");
        System.out.println("✅ 可扩展: 易于添加新命令");
        System.out.println("✅ 可撤销: 支持undo/redo操作");
        System.out.println("✅ 可组合: 支持宏命令（组合多个命令）");
        System.out.println("✅ 可记录: 可以记录命令历史");
        System.out.println("✅ 可队列: 可以将命令放入队列执行");

        System.out.println("\n=== 命令模式应用场景 ===");
        System.out.println("📌 GUI按钮/菜单: 将用户操作封装为命令");
        System.out.println("📌 事务系统: 支持回滚的事务操作");
        System.out.println("📌 宏命令: 批量执行多个操作");
        System.out.println("📌 任务调度: 将任务封装为命令放入队列");
        System.out.println("📌 日志系统: 记录操作历史");
        System.out.println("📌 撤销/重做: 编辑器、绘图软件等");
        System.out.println("📌 线程池: 将任务封装为Runnable命令");

        System.out.println("\n=== 命令模式的关键点 ===");
        System.out.println("🔑 将请求封装为对象");
        System.out.println("🔑 命令对象持有接收者引用");
        System.out.println("🔑 调用者只知道命令接口，不知道具体实现");
        System.out.println("🔑 支持撤销操作（保存状态）");
        System.out.println("🔑 支持宏命令（命令的组合）");

        System.out.println("\n=== 命令模式在Java中的应用 ===");
        System.out.println("🔸 Runnable接口: 将任务封装为命令");
        System.out.println("🔸 Swing/JavaFX: ActionListener封装按钮动作");
        System.out.println("🔸 Spring: ApplicationEvent命令模式应用");
        System.out.println("🔸 数据库事务: 可回滚的操作");
    }
}
