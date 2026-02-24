package com.skuu.design.memento;

/**
 * @author dcx
 * @description 文本编辑器类 - 备忘录模式的Originator（原发器）
 * @create 2025-01-27
 */
public class TextEditor {
    
    /**
     * 当前文本内容
     */
    private String content;
    
    /**
     * 光标位置
     */
    private int cursorPosition;
    
    public TextEditor() {
        this.content = "";
        this.cursorPosition = 0;
    }
    
    /**
     * 输入文本
     */
    public void type(String text) {
        // 在光标位置插入文本
        String before = content.substring(0, cursorPosition);
        String after = content.substring(cursorPosition);
        content = before + text + after;
        cursorPosition += text.length();
        
        System.out.println("⌨️ 输入: \"" + text + "\"");
        showStatus();
    }
    
    /**
     * 删除文本
     */
    public void delete(int count) {
        if (cursorPosition >= count) {
            String before = content.substring(0, cursorPosition - count);
            String after = content.substring(cursorPosition);
            content = before + after;
            cursorPosition -= count;
            
            System.out.println("⌫ 删除: " + count + " 个字符");
            showStatus();
        }
    }
    
    /**
     * 移动光标
     */
    public void moveCursor(int position) {
        if (position >= 0 && position <= content.length()) {
            cursorPosition = position;
            System.out.println("👆 光标移动到位置: " + position);
        }
    }
    
    /**
     * 创建备忘录（保存当前状态）
     */
    public TextMemento createMemento() {
        System.out.println("💾 保存状态...");
        return new TextMemento(content, cursorPosition);
    }
    
    /**
     * 从备忘录恢复状态
     */
    public void restoreFromMemento(TextMemento memento) {
        this.content = memento.getContent();
        this.cursorPosition = memento.getCursorPosition();
        System.out.println("↩️ 恢复状态: " + memento.getInfo());
        showStatus();
    }
    
    /**
     * 显示当前状态
     */
    public void showStatus() {
        System.out.println("   📄 内容: \"" + content + "\"");
        System.out.println("   📍 光标: " + cursorPosition);
    }
    
    /**
     * 获取当前内容
     */
    public String getContent() {
        return content;
    }
    
    /**
     * 获取光标位置
     */
    public int getCursorPosition() {
        return cursorPosition;
    }
}
