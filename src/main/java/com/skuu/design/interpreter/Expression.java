package com.skuu.design.interpreter;

/**
 * @author dcx
 * @description 抽象表达式接口 - 解释器模式的AbstractExpression
 * @create 2025-01-27
 */
public interface Expression {
    
    /**
     * 解释表达式
     * @param context 上下文，包含变量的值
     * @return 解释结果
     */
    int interpret(Context context);
    
    /**
     * 获取表达式字符串表示
     */
    String toString();
}
