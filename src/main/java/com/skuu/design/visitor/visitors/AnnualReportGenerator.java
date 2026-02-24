package com.skuu.design.visitor.visitors;

import com.skuu.design.visitor.Visitor;
import com.skuu.design.visitor.employees.*;

/**
 * @author dcx
 * @description 年度报告生成器访问者 - 具体访问者
 * @create 2025-01-27
 */
public class AnnualReportGenerator implements Visitor {
    
    private StringBuilder report;
    
    public AnnualReportGenerator() {
        report = new StringBuilder();
        report.append("📊 ========== 年度报告 ==========\n");
    }
    
    @Override
    public void visit(Engineer engineer) {
        report.append("\n👨‍💻 工程师: ").append(engineer.getName()).append("\n");
        report.append("   职位: ").append(engineer.getPosition()).append("\n");
        report.append("   工作年限: ").append(engineer.getWorkingYears()).append("年\n");
        report.append("   年度代码量: ").append(engineer.getCodeLines()).append("行\n");
        report.append("   工作评价: 代码质量优秀，技术能力强\n");
        
        System.out.println("✅ 已为 " + engineer.getName() + " 生成年度报告");
    }
    
    @Override
    public void visit(Manager manager) {
        report.append("\n👔 经理: ").append(manager.getName()).append("\n");
        report.append("   职位: ").append(manager.getPosition()).append("\n");
        report.append("   工作年限: ").append(manager.getWorkingYears()).append("年\n");
        report.append("   团队规模: ").append(manager.getTeamSize()).append("人\n");
        report.append("   完成项目: ").append(manager.getProjectCount()).append("个\n");
        report.append("   工作评价: 管理能力出色，团队协作良好\n");
        
        System.out.println("✅ 已为 " + manager.getName() + " 生成年度报告");
    }
    
    @Override
    public void visit(CEO ceo) {
        report.append("\n👨‍💼 CEO: ").append(ceo.getName()).append("\n");
        report.append("   职位: ").append(ceo.getPosition()).append("\n");
        report.append("   工作年限: ").append(ceo.getWorkingYears()).append("年\n");
        report.append("   管理部门: ").append(ceo.getDepartmentCount()).append("个\n");
        report.append("   公司营收: ¥").append(String.format("%.2f", ceo.getCompanyRevenue())).append("万\n");
        report.append("   工作评价: 战略眼光卓越，领导力强\n");
        
        System.out.println("✅ 已为 " + ceo.getName() + " 生成年度报告");
    }
    
    /**
     * 获取报告内容
     */
    public String getReport() {
        report.append("\n========== 报告结束 ==========\n");
        return report.toString();
    }
}
