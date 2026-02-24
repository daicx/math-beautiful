package com.skuu.design.memento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author dcx
 * @description 文本备忘录类 - 备忘录模式的Memento
 * @create 2025-01-27
 */
public class TextMemento {
    
    /**
     * 保存的文本内容（状态）
     */
    private final String content;
    
    /**
     * 光标位置（状态）
     */
    private final int cursorPosition;
    
    /**
     * 创建时间
     */
    private final String timestamp;
    
    /**
     * 构造函数
     * 备忘录一旦创建，状态就不能再改变（final）
     */
    public TextMemento(String content, int cursorPosition) {
        this.content = content;
        this.cursorPosition = cursorPosition;
        this.timestamp = LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    /**
     * 获取保存的内容
     * 只有原发器可以访问
     */
    String getContent() {
        return content;
    }
    
    /**
     * 获取光标位置
     */
    int getCursorPosition() {
        return cursorPosition;
    }
    
    /**
     * 获取时间戳
     */
    public String getTimestamp() {
        return timestamp;
    }
    
    /**
     * 获取备忘录信息（不暴露详细内容）
     */
    public String getInfo() {
        int length = content.length();
        String preview = length > 20 ? content.substring(0, 20) + "..." : content;
        return String.format("[%s] 文本长度: %d, 预览: \"%s\"", 
                           timestamp, length, preview);
    }
}
