package com.skuu.design.chain.handlers;

import com.skuu.design.chain.ApprovalHandler;
import com.skuu.design.chain.ApprovalResult;
import com.skuu.design.chain.LeaveRequest;

/**
 * @author dcx
 * @description 部门经理处理器 - 可以审批7天以内的请假
 * @create 2025-01-27
 */
public class DepartmentManagerHandler extends ApprovalHandler {

    @Override
    protected ApprovalResult doHandle(LeaveRequest request) {
        if (request.getLeaveDays() <= 7) {
            return ApprovalResult.approved(getHandlerName(), 
                String.format("部门经理批准%s的%d天请假申请", request.getEmployeeName(), request.getLeaveDays()));
        } else {
            return ApprovalResult.rejected(getHandlerName(), 
                String.format("请假天数超过7天，需要总经理审批"));
        }
    }

    @Override
    protected boolean canHandle(LeaveRequest request) {
        return request.getLeaveDays() <= 7;
    }

    @Override
    protected String getHandlerName() {
        return "部门经理";
    }
}
