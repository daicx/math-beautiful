package com.skuu.springdesign.chain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 责任链模式用法：请求依次经过 List&lt;Function&gt;，直到某步返回 Optional.of(result)。
 */
@SpringBootTest
class ApprovalServiceTest {

    @Autowired
    private ApprovalService approvalService;

    @Test
    void oneDayLeaveApprovedByTeamLeader() {
        ApprovalResult r = approvalService.handle(new LeaveRequest("张三", 1));
        assertTrue(r.isApproved());
        assertEquals("TeamLeader", r.getHandlerName());
    }

    @Test
    void fiveDaysLeaveApprovedByGeneralManager() {
        ApprovalResult r = approvalService.handle(new LeaveRequest("李四", 5));
        assertTrue(r.isApproved());
        assertEquals("GeneralManager", r.getHandlerName());
    }
}
