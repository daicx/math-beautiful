package com.skuu.design.visitor.visitors;

import com.skuu.design.visitor.Visitor;
import com.skuu.design.visitor.employees.*;

/**
 * @author dcx
 * @description 绩效评估访问者 - 具体访问者
 * @create 2025-01-27
 */
public class PerformanceEvaluator implements Visitor {
    
    private int totalScore = 0;
    private int count = 0;
    
    @Override
    public void visit(Engineer engineer) {
        // 工程师绩效评分 = 工作年限分 + 代码质量分
        int score = engineer.getWorkingYears() * 10 + (engineer.getCodeLines() / 10000) * 20;
        if (score > 100) score = 100;
        
        totalScore += score;
        count++;
        
        System.out.println("👨‍💻 工程师 " + engineer.getName() + " 的绩效评分: " + score + "分");
        System.out.println("   评级: " + getGrade(score));
    }
    
    @Override
    public void visit(Manager manager) {
        // 经理绩效评分 = 工作年限分 + 团队规模分 + 项目完成分
        int score = manager.getWorkingYears() * 8 + 
                   manager.getTeamSize() * 5 + 
                   manager.getProjectCount() * 10;
        if (score > 100) score = 100;
        
        totalScore += score;
        count++;
        
        System.out.println("👔 经理 " + manager.getName() + " 的绩效评分: " + score + "分");
        System.out.println("   评级: " + getGrade(score));
    }
    
    @Override
    public void visit(CEO ceo) {
        // CEO绩效评分 = 工作年限分 + 部门管理分 + 公司营收分
        int score = ceo.getWorkingYears() * 5 + 
                   ceo.getDepartmentCount() * 8 + 
                   (int)(ceo.getCompanyRevenue() / 10000) * 30;
        if (score > 100) score = 100;
        
        totalScore += score;
        count++;
        
        System.out.println("👨‍💼 CEO " + ceo.getName() + " 的绩效评分: " + score + "分");
        System.out.println("   评级: " + getGrade(score));
    }
    
    /**
     * 根据分数获取评级
     */
    private String getGrade(int score) {
        if (score >= 90) return "S (优秀)";
        if (score >= 80) return "A (良好)";
        if (score >= 70) return "B (中等)";
        if (score >= 60) return "C (及格)";
        return "D (不及格)";
    }
    
    /**
     * 获取平均绩效
     */
    public double getAverageScore() {
        return count == 0 ? 0 : (double) totalScore / count;
    }
    
    /**
     * 重置统计
     */
    public void reset() {
        totalScore = 0;
        count = 0;
    }
}
