package com.skuu.design.interpreter;

import com.skuu.design.interpreter.expressions.*;

/**
 * @author dcx
 * @description 解释器模式测试类
 * @create 2025-01-27
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("=== 解释器模式 - 数学表达式解释器示例 ===\n");

        // 创建上下文
        Context context = new Context();

        // ========== 场景1：手动构建表达式树 ==========
        System.out.println("【场景1：手动构建表达式树】\n");
        
        // 表达式: (5 + 3) * 2
        Expression exp1 = new MultiplyExpression(
            new AddExpression(
                new NumberExpression(5),
                new NumberExpression(3)
            ),
            new NumberExpression(2)
        );
        
        System.out.println("表达式: " + exp1);
        System.out.println("计算结果: " + exp1.interpret(context));
        System.out.println("验证: (5 + 3) * 2 = 16 ✅");

        // ========== 场景2：使用变量 ==========
        System.out.println("\n\n【场景2：使用变量】\n");
        
        // 设置变量
        context.setVariable("OrderCglibProxy", 10);
        context.setVariable("b", 20);
        context.setVariable("c", 5);
        
        context.showVariables();
        
        // 表达式: OrderCglibProxy + b
        Expression exp2 = new AddExpression(
            new VariableExpression("OrderCglibProxy"),
            new VariableExpression("b")
        );
        
        System.out.println("\n表达式: " + exp2);
        System.out.println("计算结果: " + exp2.interpret(context));
        System.out.println("验证: OrderCglibProxy + b = 10 + 20 = 30 ✅");
        
        // 表达式: (OrderCglibProxy + b) * c
        Expression exp3 = new MultiplyExpression(
            new AddExpression(
                new VariableExpression("OrderCglibProxy"),
                new VariableExpression("b")
            ),
            new VariableExpression("c")
        );
        
        System.out.println("\n表达式: " + exp3);
        System.out.println("计算结果: " + exp3.interpret(context));
        System.out.println("验证: (OrderCglibProxy + b) * c = (10 + 20) * 5 = 150 ✅");

        // ========== 场景3：使用解析器 ==========
        System.out.println("\n\n【场景3：使用解析器解析字符串表达式】\n");
        
        String[] expressions = {
            "10 + 5",
            "20 - 8",
            "6 * 7",
            "100 / 4",
            "OrderCglibProxy + b",
            "OrderCglibProxy * c",
            "10 + 5 * 2"
        };
        
        for (String exprStr : expressions) {
            try {
                Expression expr = ExpressionParser.parse(exprStr);
                int result = expr.interpret(context);
                System.out.println("表达式: " + exprStr + " = " + result);
            } catch (Exception e) {
                System.out.println("表达式: " + exprStr + " - 解析失败: " + e.getMessage());
            }
        }

        // ========== 场景4：复杂表达式 ==========
        System.out.println("\n\n【场景4：复杂表达式】\n");
        
        // (OrderCglibProxy + b) - (c * 2)
        Expression exp4 = new SubtractExpression(
            new AddExpression(
                new VariableExpression("OrderCglibProxy"),
                new VariableExpression("b")
            ),
            new MultiplyExpression(
                new VariableExpression("c"),
                new NumberExpression(2)
            )
        );
        
        System.out.println("表达式: " + exp4);
        System.out.println("计算结果: " + exp4.interpret(context));
        System.out.println("验证: (OrderCglibProxy + b) - (c * 2) = (10 + 20) - (5 * 2) = 20 ✅");

        // ========== 场景5：动态改变变量值 ==========
        System.out.println("\n\n【场景5：动态改变变量值】\n");
        
        Expression exp5 = ExpressionParser.parse("OrderCglibProxy + b");
        
        System.out.println("表达式: " + exp5);
        System.out.println("当 OrderCglibProxy=10, b=20 时，结果: " + exp5.interpret(context));
        
        // 改变变量值
        context.setVariable("OrderCglibProxy", 100);
        context.setVariable("b", 200);
        System.out.println("当 OrderCglibProxy=100, b=200 时，结果: " + exp5.interpret(context));

        // ========== 场景6：错误处理 ==========
        System.out.println("\n\n【场景6：错误处理】\n");
        
        // 除以0
        try {
            Expression exp6 = new DivideExpression(
                new NumberExpression(10),
                new NumberExpression(0)
            );
            System.out.println("表达式: " + exp6);
            exp6.interpret(context);
        } catch (ArithmeticException e) {
            System.out.println("❌ 错误: " + e.getMessage());
        }
        
        // 未定义的变量
        try {
            Expression exp7 = new VariableExpression("x");
            System.out.println("\n表达式: " + exp7);
            exp7.interpret(context);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ 错误: " + e.getMessage());
        }

        // ========== 总结 ==========
        System.out.println("\n\n=== 解释器模式说明 ===");
        System.out.println("1. 抽象表达式: Expression - 定义解释操作");
        System.out.println("2. 终结符表达式: NumberExpression、VariableExpression - 叶子节点");
        System.out.println("3. 非终结符表达式: AddExpression等 - 组合节点");
        System.out.println("4. 上下文: Context - 存储全局信息（变量值）");
        System.out.println("5. 解析器: ExpressionParser - 将字符串转为表达式树");

        System.out.println("\n=== 解释器模式优势 ===");
        System.out.println("✅ 易于扩展: 添加新的解释规则很容易");
        System.out.println("✅ 易于实现: 文法规则直接映射为类");
        System.out.println("✅ 灵活性: 可以动态改变和扩展语法");
        System.out.println("✅ 复用性: 表达式对象可以复用");

        System.out.println("\n=== 解释器模式缺点 ===");
        System.out.println("⚠️ 复杂文法难维护: 类数量会快速增长");
        System.out.println("⚠️ 性能问题: 递归解释可能导致性能问题");
        System.out.println("⚠️ 效率较低: 相比编译型语言效率低");

        System.out.println("\n=== 解释器模式应用场景 ===");
        System.out.println("📌 数学表达式: 计算器、公式解析");
        System.out.println("📌 SQL解析: 数据库查询语言");
        System.out.println("📌 正则表达式: 模式匹配");
        System.out.println("📌 配置文件: 配置语法解析");
        System.out.println("📌 脚本语言: 简单脚本解释器");
        System.out.println("📌 规则引擎: 业务规则解释");

        System.out.println("\n=== 解释器模式关键概念 ===");
        System.out.println("🔑 文法规则: 定义语言的语法");
        System.out.println("🔑 抽象语法树: 用树结构表示表达式");
        System.out.println("🔑 终结符: 语法的最小单位（数字、变量）");
        System.out.println("🔑 非终结符: 由其他符号组成（加法、乘法）");
        System.out.println("🔑 上下文: 存储解释过程中的全局信息");

        System.out.println("\n=== Java中的解释器模式应用 ===");
        System.out.println("🔸 java.util.regex.Pattern: 正则表达式");
        System.out.println("🔸 javax.el.ELContext: EL表达式");
        System.out.println("🔸 SpEL: Spring表达式语言");
        System.out.println("🔸 OGNL: 对象导航图语言");
        System.out.println("🔸 SQL解析器: MyBatis、Hibernate等");

        System.out.println("\n=== 何时不应使用解释器模式 ===");
        System.out.println("❌ 复杂的语法: 应使用编译器工具（ANTLR、JavaCC）");
        System.out.println("❌ 性能要求高: 应使用编译型方案");
        System.out.println("❌ 频繁变化的文法: 维护成本太高");
    }
}
