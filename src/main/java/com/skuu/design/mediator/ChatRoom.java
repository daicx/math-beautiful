package com.skuu.design.mediator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dcx
 * @description 聊天室类 - 具体中介者（ConcreteMediator）
 * @create 2025-01-27
 */
public class ChatRoom implements ChatRoomMediator {
    
    private String roomName;
    private Map<String, User> users;
    private List<String> messageHistory;
    private DateTimeFormatter formatter;
    
    public ChatRoom(String roomName) {
        this.roomName = roomName;
        this.users = new HashMap<>();
        this.messageHistory = new ArrayList<>();
        this.formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    }
    
    @Override
    public void registerUser(User user) {
        if (!users.containsKey(user.getName())) {
            users.put(user.getName(), user);
            user.setMediator(this);
            String joinMsg = "[系统] " + user.getName() + " 加入了聊天室";
            messageHistory.add(getTimestamp() + " " + joinMsg);
            System.out.println("✅ " + joinMsg);
            
            // 通知所有在线用户
            notifyAllUsers(joinMsg, user);
        }
    }
    
    @Override
    public void sendMessage(String message, User sender) {
        String timestamp = getTimestamp();
        String formattedMsg = String.format("[%s] %s: %s", 
                                          timestamp, sender.getName(), message);
        messageHistory.add(formattedMsg);
        
        System.out.println("💬 群聊消息: " + formattedMsg);
        
        // 发送给所有用户（除了发送者自己）
        for (User user : users.values()) {
            if (!user.equals(sender)) {
                user.receive(message, sender);
            }
        }
    }
    
    @Override
    public void sendPrivateMessage(String message, User sender, User receiver) {
        if (!users.containsValue(receiver)) {
            sender.receive("用户 " + receiver.getName() + " 不在聊天室", null);
            return;
        }
        
        String timestamp = getTimestamp();
        String formattedMsg = String.format("[%s] %s → %s (私聊): %s", 
                                          timestamp, sender.getName(), 
                                          receiver.getName(), message);
        messageHistory.add(formattedMsg);
        
        System.out.println("🔒 私聊消息: " + formattedMsg);
        
        // 只发送给接收者
        receiver.receive(message + " (私聊)", sender);
    }
    
    /**
     * 通知所有用户
     */
    private void notifyAllUsers(String systemMessage, User excludeUser) {
        for (User user : users.values()) {
            if (!user.equals(excludeUser)) {
                user.receive(systemMessage, null);
            }
        }
    }
    
    /**
     * 获取在线用户列表
     */
    public List<String> getOnlineUsers() {
        return new ArrayList<>(users.keySet());
    }
    
    /**
     * 获取消息历史
     */
    public List<String> getMessageHistory() {
        return new ArrayList<>(messageHistory);
    }
    
    /**
     * 获取时间戳
     */
    private String getTimestamp() {
        return LocalDateTime.now().format(formatter);
    }
    
    /**
     * 获取聊天室名称
     */
    public String getRoomName() {
        return roomName;
    }
    
    /**
     * 显示聊天室信息
     */
    public void showRoomInfo() {
        System.out.println("\n📱 ========== " + roomName + " ==========");
        System.out.println("在线用户数: " + users.size());
        System.out.println("在线用户: " + getOnlineUsers());
        System.out.println("消息数量: " + messageHistory.size());
        System.out.println("=======================================");
    }
}
