package com.skuu.design.visitor.employees;

import com.skuu.design.visitor.Employee;
import com.skuu.design.visitor.Visitor;

/**
 * @author dcx
 * @description 工程师类 - 具体元素
 * @create 2025-01-27
 */
public class Engineer implements Employee {
    
    private String name;
    private int workingYears;
    private int codeLines;
    
    public Engineer(String name, int workingYears, int codeLines) {
        this.name = name;
        this.workingYears = workingYears;
        this.codeLines = codeLines;
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String getPosition() {
        return "工程师";
    }
    
    public int getWorkingYears() {
        return workingYears;
    }
    
    public int getCodeLines() {
        return codeLines;
    }
}
