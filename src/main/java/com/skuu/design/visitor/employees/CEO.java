package com.skuu.design.visitor.employees;

import com.skuu.design.visitor.Employee;
import com.skuu.design.visitor.Visitor;

/**
 * @author dcx
 * @description CEO类 - 具体元素
 * @create 2025-01-27
 */
public class CEO implements Employee {
    
    private String name;
    private int workingYears;
    private int departmentCount;
    private double companyRevenue;
    
    public CEO(String name, int workingYears, int departmentCount, double companyRevenue) {
        this.name = name;
        this.workingYears = workingYears;
        this.departmentCount = departmentCount;
        this.companyRevenue = companyRevenue;
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
        return "CEO";
    }
    
    public int getWorkingYears() {
        return workingYears;
    }
    
    public int getDepartmentCount() {
        return departmentCount;
    }
    
    public double getCompanyRevenue() {
        return companyRevenue;
    }
}
