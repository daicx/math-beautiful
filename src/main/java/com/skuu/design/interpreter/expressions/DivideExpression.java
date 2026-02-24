package com.skuu.design.interpreter.expressions;

import com.skuu.design.interpreter.Context;
import com.skuu.design.interpreter.Expression;

/**
 * @author dcx
 * @description 除法表达式 - 非终结符表达式
 * @create 2025-01-27
 */
public class DivideExpression implements Expression {
    
    private Expression left;
    private Expression right;
    
    public DivideExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public int interpret(Context context) {
        int divisor = right.interpret(context);
        if (divisor == 0) {
            throw new ArithmeticException("除数不能为0");
        }
        return left.interpret(context) / divisor;
    }
    
    @Override
    public String toString() {
        return "(" + left.toString() + " / " + right.toString() + ")";
    }
}
