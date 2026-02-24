package com.skuu.design.interpreter;

import com.skuu.design.interpreter.expressions.*;

import java.util.Stack;

/**
 * @author dcx
 * @description 表达式解析器 - 将字符串解析为表达式树
 * @create 2025-01-27
 */
public class ExpressionParser {
    
    /**
     * 解析表达式字符串
     * 支持格式: "OrderCglibProxy + b", "10 * 5", "x - y + z" 等
     * 使用后缀表达式（逆波兰表达式）解析
     * 
     * @param expression 表达式字符串，如 "OrderCglibProxy + b * c"
     * @return Expression对象
     */
    public static Expression parse(String expression) {
        // 简化版：只支持两个操作数的简单表达式
        String[] tokens = expression.trim().split("\\s+");
        
        if (tokens.length == 1) {
            // 单个数字或变量
            return parseToken(tokens[0]);
        } else if (tokens.length == 3) {
            // 简单二元表达式: "OrderCglibProxy + b"
            Expression left = parseToken(tokens[0]);
            String operator = tokens[1];
            Expression right = parseToken(tokens[2]);
            
            return createBinaryExpression(left, operator, right);
        } else {
            // 复杂表达式，使用栈解析
            return parseComplexExpression(tokens);
        }
    }
    
    /**
     * 解析单个token（数字或变量）
     */
    private static Expression parseToken(String token) {
        try {
            // 尝试解析为数字
            int number = Integer.parseInt(token);
            return new NumberExpression(number);
        } catch (NumberFormatException e) {
            // 解析为变量
            return new VariableExpression(token);
        }
    }
    
    /**
     * 创建二元表达式
     */
    private static Expression createBinaryExpression(Expression left, String operator, Expression right) {
        switch (operator) {
            case "+":
                return new AddExpression(left, right);
            case "-":
                return new SubtractExpression(left, right);
            case "*":
                return new MultiplyExpression(left, right);
            case "/":
                return new DivideExpression(left, right);
            default:
                throw new IllegalArgumentException("不支持的运算符: " + operator);
        }
    }
    
    /**
     * 解析复杂表达式（简化版，使用优先级处理）
     */
    private static Expression parseComplexExpression(String[] tokens) {
        Stack<Expression> stack = new Stack<>();
        Stack<String> operators = new Stack<>();
        
        for (String token : tokens) {
            if (isOperator(token)) {
                // 处理运算符优先级
                while (!operators.isEmpty() && 
                       getPriority(operators.peek()) >= getPriority(token)) {
                    String op = operators.pop();
                    Expression right = stack.pop();
                    Expression left = stack.pop();
                    stack.push(createBinaryExpression(left, op, right));
                }
                operators.push(token);
            } else {
                // 操作数
                stack.push(parseToken(token));
            }
        }
        
        // 处理剩余的运算符
        while (!operators.isEmpty()) {
            String op = operators.pop();
            Expression right = stack.pop();
            Expression left = stack.pop();
            stack.push(createBinaryExpression(left, op, right));
        }
        
        return stack.pop();
    }
    
    /**
     * 判断是否是运算符
     */
    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || 
               token.equals("*") || token.equals("/");
    }
    
    /**
     * 获取运算符优先级
     */
    private static int getPriority(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return 0;
        }
    }
}
