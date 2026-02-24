package com.skuu.design.chain;

import lombok.Data;

/**
 * @author dcx
 * @description 审批结果
 * @create 2025-01-27
 */
@Data
public class ApprovalResult {
    /**
     * 是否通过
     */
    private boolean approved;
    
    /**
     * 审批人
     */
    private String approver;
    
    /**
     * 审批意见
     */
    private String comment;

    public ApprovalResult(boolean approved, String approver, String comment) {
        this.approved = approved;
        this.approver = approver;
        this.comment = comment;
    }

    public static ApprovalResult approved(String approver, String comment) {
        return new ApprovalResult(true, approver, comment);
    }

    public static ApprovalResult rejected(String approver, String comment) {
        return new ApprovalResult(false, approver, comment);
    }

    @Override
    public String toString() {
        return String.format("审批结果: %s, 审批人: %s, 意见: %s", 
                           approved ? "通过" : "拒绝", approver, comment);
    }
}
