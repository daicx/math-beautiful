package com.skuu.design.chain;

import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dcx
 * @description 请假请求对象
 * @create 2025-01-27
 */
@Data
public class LeaveRequest {
    /**
     * 员工姓名
     */
    private String employeeName;
    
    /**
     * 请假天数
     */
    private int leaveDays;
    
    /**
     * 请假原因
     */
    private String reason;

    public LeaveRequest(String employeeName, int leaveDays, String reason) {
        this.employeeName = employeeName;
        this.leaveDays = leaveDays;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return String.format("员工: %s, 请假天数: %d, 请假原因: %s", 
                           employeeName, leaveDays, reason);
    }
}
