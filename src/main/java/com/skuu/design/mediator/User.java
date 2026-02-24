package com.skuu.design.mediator;

/**
 * @author dcx
 * @description 用户抽象类 - 中介者模式的Colleague（同事类）
 * @create 2025-01-27
 */
public abstract class User {
    
    protected String name;
    protected ChatRoomMediator mediator;
    
    public User(String name) {
        this.name = name;
    }
    
    /**
     * 设置中介者
     */
    public void setMediator(ChatRoomMediator mediator) {
        this.mediator = mediator;
    }
    
    /**
     * 发送消息（通过中介者）
     */
    public abstract void send(String message);
    
    /**
     * 发送私聊消息（通过中介者）
     */
    public abstract void sendPrivate(String message, User receiver);
    
    /**
     * 接收消息
     */
    public abstract void receive(String message, User sender);
    
    /**
     * 获取用户名
     */
    public String getName() {
        return name;
    }
}
