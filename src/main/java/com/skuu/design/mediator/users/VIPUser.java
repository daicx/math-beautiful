package com.skuu.design.mediator.users;

import com.skuu.design.mediator.User;

/**
 * @author dcx
 * @description VIP用户 - 具体同事类
 * @create 2025-01-27
 */
public class VIPUser extends User {
    
    public VIPUser(String name) {
        super(name);
    }
    
    @Override
    public void send(String message) {
        System.out.println("👑 [VIP-" + name + "] 发送群聊: " + message);
        mediator.sendMessage(message, this);
    }
    
    @Override
    public void sendPrivate(String message, User receiver) {
        System.out.println("👑 [VIP-" + name + "] 发送私聊给 " + receiver.getName() + ": " + message);
        mediator.sendPrivateMessage(message, this, receiver);
    }
    
    @Override
    public void receive(String message, User sender) {
        if (sender == null) {
            // 系统消息
            System.out.println("  ← [VIP-" + name + "] 收到系统消息: " + message);
        } else {
            System.out.println("  ← [VIP-" + name + "] 收到来自 " + sender.getName() + " 的消息: " + message);
        }
    }
}
