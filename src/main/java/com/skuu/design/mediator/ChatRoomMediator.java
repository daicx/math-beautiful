package com.skuu.design.mediator;

/**
 * @author dcx
 * @description 聊天室中介者接口 - 中介者模式的Mediator
 * @create 2025-01-27
 */
public interface ChatRoomMediator {
    
    /**
     * 注册用户
     */
    void registerUser(User user);
    
    /**
     * 发送消息
     * @param message 消息内容
     * @param sender 发送者
     */
    void sendMessage(String message, User sender);
    
    /**
     * 发送私聊消息
     * @param message 消息内容
     * @param sender 发送者
     * @param receiver 接收者
     */
    void sendPrivateMessage(String message, User sender, User receiver);
}
