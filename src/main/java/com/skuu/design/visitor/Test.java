package com.skuu.design.visitor;

import com.skuu.design.visitor.employees.*;
import com.skuu.design.visitor.visitors.*;

/**
 * @author dcx
 * @description 访问者模式测试类
 * @create 2025-01-27
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("=== 访问者模式 - 公司员工管理示例 ===\n");

        // 创建公司（对象结构）
        Company company = new Company("科技创新公司");

        System.out.println("【初始化公司员工】\n");
        
        // 创建员工（元素）
        Employee engineer1 = new Engineer("张三", 3, 50000);
        Employee engineer2 = new Engineer("李四", 5, 80000);
        Employee manager1 = new Manager("王五", 8, 10, 15);
        Employee manager2 = new Manager("赵六", 6, 8, 12);
        Employee ceo = new CEO("刘总", 15, 5, 50000);

        // 添加员工到公司
        company.addEmployee(engineer1);
        company.addEmployee(engineer2);
        company.addEmployee(manager1);
        company.addEmployee(manager2);
        company.addEmployee(ceo);

        // 显示公司信息
        company.showCompanyInfo();

        // ========== 场景1：薪资计算 ==========
        System.out.println("\n\n【场景1：薪资计算访问者】");
        
        SalaryCalculator salaryCalculator = new SalaryCalculator();
        company.accept(salaryCalculator);
        
        System.out.println("\n💰 公司总薪资: ¥" + String.format("%.2f", salaryCalculator.getTotalSalary()));
        System.out.println("💰 平均薪资: ¥" + String.format("%.2f", salaryCalculator.getTotalSalary() / company.getEmployeeCount()));

        // ========== 场景2：绩效评估 ==========
        System.out.println("\n\n【场景2：绩效评估访问者】");
        
        PerformanceEvaluator performanceEvaluator = new PerformanceEvaluator();
        company.accept(performanceEvaluator);
        
        System.out.println("\n📈 平均绩效: " + String.format("%.1f", performanceEvaluator.getAverageScore()) + "分");

        // ========== 场景3：年度报告生成 ==========
        System.out.println("\n\n【场景3：年度报告生成访问者】");
        
        AnnualReportGenerator reportGenerator = new AnnualReportGenerator();
        company.accept(reportGenerator);
        
        System.out.println("\n📄 年度报告内容:");
        System.out.println(reportGenerator.getReport());

        // ========== 场景4：添加新的访问者 ==========
        System.out.println("\n【场景4：演示添加新访问者的便利性】");
        System.out.println("假设现在需要添加新功能：统计员工信息");
        System.out.println("✅ 只需要创建一个新的Visitor实现类");
        System.out.println("❌ 不需要修改任何Employee类");
        System.out.println("这就是访问者模式的优势！");

        // ========== 对比不使用访问者模式 ==========
        System.out.println("\n\n【对比：不使用访问者模式】\n");
        
        System.out.println("❌ 不使用访问者模式:");
        System.out.println("  - 需要在每个员工类中添加计算薪资、评估绩效等方法");
        System.out.println("  - 添加新操作时，需要修改所有员工类");
        System.out.println("  - 违反开闭原则，维护成本高");
        System.out.println("  - 代码分散，难以统一管理");
        System.out.println("\n  示例代码:");
        System.out.println("  class Engineer {");
        System.out.println("      double calculateSalary() { ... }");
        System.out.println("      int evaluatePerformance() { ... }");
        System.out.println("      String generateReport() { ... }");
        System.out.println("      // 每次新增功能都要修改这里");
        System.out.println("  }");
        
        System.out.println("\n✅ 使用访问者模式:");
        System.out.println("  - 员工类保持稳定，只需实现accept()方法");
        System.out.println("  - 添加新操作只需创建新的Visitor");
        System.out.println("  - 符合开闭原则，易于扩展");
        System.out.println("  - 相关操作集中在访问者中");
        System.out.println("\n  示例代码:");
        System.out.println("  class Engineer {");
        System.out.println("      void accept(Visitor visitor) {");
        System.out.println("          visitor.visit(this);");
        System.out.println("      }");
        System.out.println("      // 添加新功能不需要修改这里");
        System.out.println("  }");

        // ========== 总结 ==========
        System.out.println("\n\n=== 访问者模式说明 ===");
        System.out.println("1. 访问者接口: Visitor - 为每种元素定义访问方法");
        System.out.println("2. 具体访问者: SalaryCalculator等 - 实现具体的访问操作");
        System.out.println("3. 元素接口: Employee - 定义accept()方法");
        System.out.println("4. 具体元素: Engineer、Manager、CEO - 实现accept()");
        System.out.println("5. 对象结构: Company - 存储元素集合");

        System.out.println("\n=== 访问者模式优势 ===");
        System.out.println("✅ 符合开闭原则: 添加新操作无需修改元素类");
        System.out.println("✅ 相关操作集中: 同一操作的代码集中在一起");
        System.out.println("✅ 易于添加新操作: 只需创建新的访问者");
        System.out.println("✅ 数据结构稳定: 元素类结构保持稳定");
        System.out.println("✅ 灵活性强: 可以为不同元素定义不同操作");

        System.out.println("\n=== 访问者模式缺点 ===");
        System.out.println("⚠️ 难以添加新元素: 需要修改所有访问者");
        System.out.println("⚠️ 破坏封装: 访问者需要访问元素的内部数据");
        System.out.println("⚠️ 依赖具体类: 访问者依赖具体的元素类");

        System.out.println("\n=== 访问者模式应用场景 ===");
        System.out.println("📌 编译器: AST遍历、代码生成、语义检查");
        System.out.println("📌 文档处理: XML/DOM树的遍历和操作");
        System.out.println("📌 报表生成: 不同格式的报表生成");
        System.out.println("📌 对象持久化: 将对象保存为不同格式");
        System.out.println("📌 数据统计: 对集合进行各种统计操作");
        System.out.println("📌 权限检查: 对不同类型对象进行权限验证");

        System.out.println("\n=== 访问者模式关键点 ===");
        System.out.println("🔑 双分派: 运行时根据两个对象类型确定方法");
        System.out.println("🔑 元素稳定: 元素类结构不经常变化");
        System.out.println("🔑 操作多变: 经常需要添加新的操作");
        System.out.println("🔑 accept()方法: 每个元素都要实现");

        System.out.println("\n=== 双分派机制 ===");
        System.out.println("第一次分派: employee.accept(visitor)");
        System.out.println("第二次分派: visitor.visit(this)");
        System.out.println("结果: 运行时确定具体的visit(Engineer)方法");

        System.out.println("\n=== 何时使用访问者模式 ===");
        System.out.println("✅ 对象结构稳定，不经常添加新元素");
        System.out.println("✅ 经常需要对对象结构中的元素进行新操作");
        System.out.println("✅ 需要对不同类型的元素执行不同操作");
        System.out.println("✅ 对象结构包含多种类型的对象");

        System.out.println("\n=== 何时不使用访问者模式 ===");
        System.out.println("❌ 对象结构经常变化（添加新元素）");
        System.out.println("❌ 元素类需要保持高度封装");
        System.out.println("❌ 操作类型固定，不需要经常添加");

        System.out.println("\n=== Java中的访问者模式应用 ===");
        System.out.println("🔸 javax.lang.model.element.Element: Java编译器API");
        System.out.println("🔸 org.w3c.dom.Node: DOM树遍历");
        System.out.println("🔸 ASM/ByteBuddy: 字节码操作");
        System.out.println("🔸 文件系统: java.nio.file.FileVisitor");
    }
}
