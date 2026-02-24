package com.skuu.design.chain;

import com.skuu.design.chain.handlers.*;

/**
 * @author dcx
 * @description 责任链模式测试类
 * @create 2025-01-27
 */
public class Test {

    public static void main(String[] args) {
        // 创建责任链
        ApprovalHandler teamLeader = new TeamLeaderHandler();
        ApprovalHandler departmentManager = new DepartmentManagerHandler();
        ApprovalHandler generalManager = new GeneralManagerHandler();
        ApprovalHandler board = new BoardHandler();

        // 设置责任链的顺序：团队负责人 -> 部门经理 -> 总经理 -> 董事会
        teamLeader.setNext(departmentManager);
        departmentManager.setNext(generalManager);
        generalManager.setNext(board);

        // 创建测试用例
        LeaveRequest[] testCases = {
            new LeaveRequest("张三", 2, "感冒发烧"),
            new LeaveRequest("李四", 5, "家里有事"),
            new LeaveRequest("王五", 10, "旅游度假"),
            new LeaveRequest("赵六", 25, "探亲访友"),
            new LeaveRequest("钱七", 60, "长期病假"),
            new LeaveRequest("孙八", 120, "创业准备")
        };

        System.out.println("=== 责任链模式 - 请假审批系统 ===\n");

        // 测试各种请假申请
        for (LeaveRequest request : testCases) {
            System.out.println("提交申请: " + request);
            ApprovalResult result = teamLeader.handle(request);
            System.out.println("审批结果: " + result);
            System.out.println("----------------------------------------");
        }

        System.out.println("\n=== 责任链模式说明 ===");
        System.out.println("1. 团队负责人: 可审批3天以内");
        System.out.println("2. 部门经理: 可审批7天以内");
        System.out.println("3. 总经理: 可审批30天以内");
        System.out.println("4. 董事会: 可审批90天以内");
        System.out.println("5. 超过90天: 建议停薪留职");
    }
}
