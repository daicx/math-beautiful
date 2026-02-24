package com.skuu.design.visitor.visitors;

import com.skuu.design.visitor.Visitor;
import com.skuu.design.visitor.employees.*;

/**
 * @author dcx
 * @description 薪资计算访问者 - 具体访问者
 * @create 2025-01-27
 */
public class SalaryCalculator implements Visitor {
    
    private double totalSalary = 0;
    
    @Override
    public void visit(Engineer engineer) {
        // 工程师薪资 = 基本工资 + 工作年限奖金 + 代码行数奖金
        double salary = 10000 + engineer.getWorkingYears() * 1000 + engineer.getCodeLines() / 1000.0 * 500;
        totalSalary += salary;
        
        System.out.println("👨‍💻 工程师 " + engineer.getName() + " 的薪资: ¥" + String.format("%.2f", salary));
        System.out.println("   工作年限: " + engineer.getWorkingYears() + "年");
        System.out.println("   代码行数: " + engineer.getCodeLines() + "行");
    }
    
    @Override
    public void visit(Manager manager) {
        // 经理薪资 = 基本工资 + 工作年限奖金 + 团队规模奖金 + 项目数量奖金
        double salary = 20000 + manager.getWorkingYears() * 2000 + 
                       manager.getTeamSize() * 500 + manager.getProjectCount() * 1000;
        totalSalary += salary;
        
        System.out.println("👔 经理 " + manager.getName() + " 的薪资: ¥" + String.format("%.2f", salary));
        System.out.println("   工作年限: " + manager.getWorkingYears() + "年");
        System.out.println("   团队规模: " + manager.getTeamSize() + "人");
        System.out.println("   项目数量: " + manager.getProjectCount() + "个");
    }
    
    @Override
    public void visit(CEO ceo) {
        // CEO薪资 = 基本工资 + 工作年限奖金 + 部门数量奖金 + 公司营收提成
        double salary = 50000 + ceo.getWorkingYears() * 5000 + 
                       ceo.getDepartmentCount() * 2000 + ceo.getCompanyRevenue() * 0.01;
        totalSalary += salary;
        
        System.out.println("👨‍💼 CEO " + ceo.getName() + " 的薪资: ¥" + String.format("%.2f", salary));
        System.out.println("   工作年限: " + ceo.getWorkingYears() + "年");
        System.out.println("   部门数量: " + ceo.getDepartmentCount() + "个");
        System.out.println("   公司营收: ¥" + String.format("%.2f", ceo.getCompanyRevenue()) + "万");
    }
    
    /**
     * 获取总薪资
     */
    public double getTotalSalary() {
        return totalSalary;
    }
    
    /**
     * 重置总薪资
     */
    public void reset() {
        totalSalary = 0;
    }
}
