package com.skuu.design.visitor.employees;

import com.skuu.design.visitor.Employee;
import com.skuu.design.visitor.Visitor;

/**
 * @author dcx
 * @description 经理类 - 具体元素
 * @create 2025-01-27
 */
public class Manager implements Employee {
    
    private String name;
    private int workingYears;
    private int teamSize;
    private int projectCount;
    
    public Manager(String name, int workingYears, int teamSize, int projectCount) {
        this.name = name;
        this.workingYears = workingYears;
        this.teamSize = teamSize;
        this.projectCount = projectCount;
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
        return "经理";
    }
    
    public int getWorkingYears() {
        return workingYears;
    }
    
    public int getTeamSize() {
        return teamSize;
    }
    
    public int getProjectCount() {
        return projectCount;
    }
}
