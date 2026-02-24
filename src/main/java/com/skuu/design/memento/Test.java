package com.skuu.design.memento;

/**
 * @author dcx
 * @description 备忘录模式测试类
 * @create 2025-01-27
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("=== 备忘录模式 - 文本编辑器示例 ===\n");

        // 创建文本编辑器（原发器）
        TextEditor editor = new TextEditor();
        
        // 创建历史管理器（负责人）
        History history = new History(10);

        // ========== 场景1：基本编辑操作 ==========
        System.out.println("【场景1：基本编辑操作】\n");
        
        // 初始状态
        history.saveState(editor.createMemento());
        
        // 输入"Hello"
        editor.type("Hello");
        history.saveState(editor.createMemento());
        
        // 输入" World"
        editor.type(" World");
        history.saveState(editor.createMemento());
        
        // 输入"!"
        editor.type("!");
        history.saveState(editor.createMemento());

        System.out.println("\n当前状态:");
        editor.showStatus();

        // ========== 场景2：撤销操作 ==========
        System.out.println("\n\n【场景2：撤销操作】\n");
        
        // 撤销一次
        TextMemento memento = history.undo(editor.createMemento());
        if (memento != null) {
            editor.restoreFromMemento(memento);
        }
        
        // 再撤销一次
        memento = history.undo(editor.createMemento());
        if (memento != null) {
            editor.restoreFromMemento(memento);
        }

        // ========== 场景3：重做操作 ==========
        System.out.println("\n\n【场景3：重做操作】\n");
        
        // 重做一次
        memento = history.redo(editor.createMemento());
        if (memento != null) {
            editor.restoreFromMemento(memento);
        }

        // ========== 场景4：编辑后撤销栈清空 ==========
        System.out.println("\n\n【场景4：新编辑操作会清空重做栈】\n");
        
        // 进行新的编辑
        editor.type("?");
        history.saveState(editor.createMemento());
        
        System.out.println("\n尝试重做:");
        memento = history.redo(editor.createMemento());
        
        // ========== 场景5：复杂编辑序列 ==========
        System.out.println("\n\n【场景5：复杂编辑序列】\n");
        
        // 清空编辑器
        editor = new TextEditor();
        history.clear();
        
        // 保存初始状态
        history.saveState(editor.createMemento());
        
        // 编辑序列
        String[] inputs = {"Java", " ", "Design", " ", "Patterns"};
        for (String input : inputs) {
            editor.type(input);
            history.saveState(editor.createMemento());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("\n最终状态:");
        editor.showStatus();
        
        // 显示历史记录
        history.showHistory();
        
        // 连续撤销
        System.out.println("\n\n连续撤销3次:");
        for (int i = 0; i < 3; i++) {
            memento = history.undo(editor.createMemento());
            if (memento != null) {
                editor.restoreFromMemento(memento);
            }
        }

        // ========== 场景6：删除操作 ==========
        System.out.println("\n\n【场景6：删除操作】\n");
        
        editor.delete(3);
        history.saveState(editor.createMemento());
        
        // 撤销删除
        System.out.println("\n撤销删除:");
        memento = history.undo(editor.createMemento());
        if (memento != null) {
            editor.restoreFromMemento(memento);
        }

        // ========== 对比不使用备忘录模式 ==========
        System.out.println("\n\n【对比：不使用备忘录模式】\n");
        
        System.out.println("❌ 不使用备忘录模式:");
        System.out.println("  - 需要手动记录每个状态变量");
        System.out.println("  - 增加新状态变量时，所有保存/恢复代码都要修改");
        System.out.println("  - 状态管理代码分散，难以维护");
        System.out.println("  - 破坏封装性，外部需要知道所有状态细节");
        
        System.out.println("\n✅ 使用备忘录模式:");
        System.out.println("  - 自动保存完整状态");
        System.out.println("  - 增加状态变量只需修改备忘录类");
        System.out.println("  - 状态管理集中，易于维护");
        System.out.println("  - 保持封装性，外部不需要知道状态细节");

        // ========== 总结 ==========
        System.out.println("\n\n=== 备忘录模式说明 ===");
        System.out.println("1. 备忘录: TextMemento - 保存状态的快照");
        System.out.println("2. 原发器: TextEditor - 创建和恢复备忘录");
        System.out.println("3. 负责人: History - 管理备忘录（撤销/重做栈）");
        System.out.println("4. 封装性: 备忘录状态对外不可见");

        System.out.println("\n=== 备忘录模式优势 ===");
        System.out.println("✅ 保存和恢复状态: 实现撤销/重做功能");
        System.out.println("✅ 封装性: 不破坏对象的封装边界");
        System.out.println("✅ 简化原发器: 状态管理逻辑分离");
        System.out.println("✅ 状态独立: 每个备忘录独立存储");

        System.out.println("\n=== 备忘录模式缺点 ===");
        System.out.println("⚠️ 内存消耗: 频繁保存会占用大量内存");
        System.out.println("⚠️ 性能开销: 创建备忘录需要复制状态");
        System.out.println("⚠️ 生命周期管理: 需要管理备忘录的创建和销毁");

        System.out.println("\n=== 备忘录模式应用场景 ===");
        System.out.println("📌 撤销/重做: 文本编辑器、图形编辑器");
        System.out.println("📌 事务回滚: 数据库事务、业务操作");
        System.out.println("📌 游戏存档: 保存游戏进度");
        System.out.println("📌 浏览器历史: 前进/后退功能");
        System.out.println("📌 工作流: 流程状态保存和恢复");
        System.out.println("📌 快照备份: 系统状态快照");

        System.out.println("\n=== 备忘录模式关键点 ===");
        System.out.println("🔑 备忘录不可变: 一旦创建不能修改（final）");
        System.out.println("🔑 窄接口: 负责人只能传递备忘录，不能访问内容");
        System.out.println("🔑 宽接口: 原发器可以访问备忘录的所有内容");
        System.out.println("🔑 封装边界: 备忘录保护原发器的内部状态");

        System.out.println("\n=== Java中的备忘录模式应用 ===");
        System.out.println("🔸 Serializable: Java序列化机制");
        System.out.println("🔸 Cloneable: 对象克隆");
        System.out.println("🔸 JTextComponent: Swing的撤销/重做");
        System.out.println("🔸 HttpSession: Web会话状态保存");

        // 最终显示历史记录
        System.out.println("\n\n最终历史记录:");
        history.showHistory();
        
        System.out.println("\n\n当前编辑器状态:");
        editor.showStatus();
    }
}
