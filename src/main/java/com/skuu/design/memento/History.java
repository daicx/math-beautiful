package com.skuu.design.memento;

import java.util.Stack;

/**
 * @author dcx
 * @description 历史管理类 - 备忘录模式的Caretaker（负责人）
 * @create 2025-01-27
 */
public class History {
    
    /**
     * 撤销栈（保存历史状态）
     */
    private Stack<TextMemento> undoStack;
    
    /**
     * 重做栈（保存被撤销的状态）
     */
    private Stack<TextMemento> redoStack;
    
    /**
     * 最大历史记录数
     */
    private final int maxHistorySize;
    
    public History() {
        this(50);  // 默认保存50个历史记录
    }
    
    public History(int maxHistorySize) {
        this.maxHistorySize = maxHistorySize;
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }
    
    /**
     * 保存状态
     */
    public void saveState(TextMemento memento) {
        // 新的操作会清空重做栈
        redoStack.clear();
        
        // 如果超过最大历史数，移除最早的记录
        if (undoStack.size() >= maxHistorySize) {
            undoStack.remove(0);
        }
        
        undoStack.push(memento);
        System.out.println("📚 历史记录已保存 (撤销栈: " + undoStack.size() + ")");
    }
    
    /**
     * 撤销（返回上一个状态）
     */
    public TextMemento undo(TextMemento currentState) {
        if (canUndo()) {
            // 保存当前状态到重做栈
            redoStack.push(currentState);
            
            // 从撤销栈取出上一个状态
            TextMemento memento = undoStack.pop();
            System.out.println("⬅️ 撤销 (撤销栈: " + undoStack.size() + ", 重做栈: " + redoStack.size() + ")");
            return memento;
        }
        System.out.println("❌ 没有可撤销的操作");
        return null;
    }
    
    /**
     * 重做（返回下一个状态）
     */
    public TextMemento redo(TextMemento currentState) {
        if (canRedo()) {
            // 保存当前状态到撤销栈
            undoStack.push(currentState);
            
            // 从重做栈取出下一个状态
            TextMemento memento = redoStack.pop();
            System.out.println("➡️ 重做 (撤销栈: " + undoStack.size() + ", 重做栈: " + redoStack.size() + ")");
            return memento;
        }
        System.out.println("❌ 没有可重做的操作");
        return null;
    }
    
    /**
     * 是否可以撤销
     */
    public boolean canUndo() {
        return !undoStack.isEmpty();
    }
    
    /**
     * 是否可以重做
     */
    public boolean canRedo() {
        return !redoStack.isEmpty();
    }
    
    /**
     * 清空历史
     */
    public void clear() {
        undoStack.clear();
        redoStack.clear();
        System.out.println("🗑️ 历史记录已清空");
    }
    
    /**
     * 显示历史记录
     */
    public void showHistory() {
        System.out.println("\n📜 历史记录:");
        System.out.println("撤销栈 (" + undoStack.size() + " 条):");
        for (int i = undoStack.size() - 1; i >= 0; i--) {
            System.out.println("  " + (i + 1) + ". " + undoStack.get(i).getInfo());
        }
        
        if (!redoStack.isEmpty()) {
            System.out.println("\n重做栈 (" + redoStack.size() + " 条):");
            for (int i = redoStack.size() - 1; i >= 0; i--) {
                System.out.println("  " + (i + 1) + ". " + redoStack.get(i).getInfo());
            }
        }
    }
    
    /**
     * 获取历史记录数量
     */
    public int getHistorySize() {
        return undoStack.size();
    }
}
