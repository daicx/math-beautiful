package com.skuu.design.chain.handlers;

import com.skuu.design.chain.ApprovalHandler;
import com.skuu.design.chain.ApprovalResult;
import com.skuu.design.chain.LeaveRequest;

/**
 * @author dcx
 * @description 董事会处理器 - 处理超长请假申请
 * @create 2025-01-27
 */
public class BoardHandler extends ApprovalHandler {

    @Override
    protected ApprovalResult doHandle(LeaveRequest request) {
        if (request.getLeaveDays() <= 90) {
            return ApprovalResult.approved(getHandlerName(), 
                String.format("董事会批准%s的%d天请假申请", request.getEmployeeName(), request.getLeaveDays()));
        } else {
            return ApprovalResult.rejected(getHandlerName(), 
                String.format("请假天数超过90天，建议办理停薪留职"));
        }
    }

    @Override
    protected boolean canHandle(LeaveRequest request) {
        return true; // 董事会是最高级别，可以处理所有请求
    }

    @Override
    protected String getHandlerName() {
        return "董事会";
    }
}
