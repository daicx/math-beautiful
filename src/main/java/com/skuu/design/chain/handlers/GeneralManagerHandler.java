package com.skuu.design.chain.handlers;

import com.skuu.design.chain.ApprovalHandler;
import com.skuu.design.chain.ApprovalResult;
import com.skuu.design.chain.LeaveRequest;

/**
 * @author dcx
 * @description 总经理处理器 - 可以审批任意天数的请假
 * @create 2025-01-27
 */
public class GeneralManagerHandler extends ApprovalHandler {

    @Override
    protected ApprovalResult doHandle(LeaveRequest request) {
        if (request.getLeaveDays() <= 30) {
            return ApprovalResult.approved(getHandlerName(), 
                String.format("总经理批准%s的%d天请假申请", request.getEmployeeName(), request.getLeaveDays()));
        } else {
            return ApprovalResult.rejected(getHandlerName(), 
                String.format("请假天数超过30天，需要董事会决议"));
        }
    }

    @Override
    protected boolean canHandle(LeaveRequest request) {
        return request.getLeaveDays() <= 30;
    }

    @Override
    protected String getHandlerName() {
        return "总经理";
    }
}
