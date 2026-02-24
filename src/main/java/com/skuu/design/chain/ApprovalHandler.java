package com.skuu.design.chain;

/**
 * @author dcx
 * @description 抽象审批处理器
 * @create 2025-01-27
 */
public abstract class ApprovalHandler {
    
    /**
     * 下一个处理器
     */
    protected ApprovalHandler nextHandler;
    
    /**
     * 设置下一个处理器
     */
    public void setNext(ApprovalHandler handler) {
        this.nextHandler = handler;
    }
    
    /**
     * 处理请求
     */
    public final ApprovalResult handle(LeaveRequest request) {
        // 当前处理器处理请求
        ApprovalResult result = doHandle(request);
        
        // 如果当前处理器无法处理或拒绝，则传递给下一个处理器
        if (nextHandler != null && (!result.isApproved() || !canHandle(request))) {
            result = nextHandler.handle(request);
        }
        
        return result;
    }
    
    /**
     * 具体的处理逻辑，由子类实现
     */
    protected abstract ApprovalResult doHandle(LeaveRequest request);
    
    /**
     * 判断当前处理器是否可以处理该请求
     */
    protected abstract boolean canHandle(LeaveRequest request);
    
    /**
     * 获取处理器名称
     */
    protected abstract String getHandlerName();
}
