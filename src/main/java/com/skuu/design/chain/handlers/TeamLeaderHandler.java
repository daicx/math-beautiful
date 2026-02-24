package com.skuu.design.chain.handlers;

import com.skuu.design.chain.ApprovalHandler;
import com.skuu.design.chain.ApprovalResult;
import com.skuu.design.chain.LeaveRequest;

/**
 * @author dcx
 * @description 团队负责人处理器 - 可以审批3天以内的请假
 * @create 2025-01-27
 */
public class TeamLeaderHandler extends ApprovalHandler {

    @Override
    protected ApprovalResult doHandle(LeaveRequest request) {
        if (request.getLeaveDays() <= 3) {
            return ApprovalResult.approved(getHandlerName(), 
                String.format("团队负责人批准%s的%d天请假申请", request.getEmployeeName(), request.getLeaveDays()));
        } else {
            return ApprovalResult.rejected(getHandlerName(), 
                String.format("请假天数超过3天，需要上级审批"));
        }
    }

    @Override
    protected boolean canHandle(LeaveRequest request) {
        return request.getLeaveDays() <= 3;
    }

    @Override
    protected String getHandlerName() {
        return "团队负责人";
    }
}
