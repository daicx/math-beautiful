package com.skuu.design.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dcx
 * @description 公司类 - 对象结构（ObjectStructure）
 * @create 2025-01-27
 */
public class Company {
    
    private String name;
    private List<Employee> employees;
    
    public Company(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }
    
    /**
     * 添加员工
     */
    public void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("➕ 添加员工: " + employee.getName() + " (" + employee.getPosition() + ")");
    }
    
    /**
     * 移除员工
     */
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        System.out.println("➖ 移除员工: " + employee.getName());
    }
    
    /**
     * 接受访问者访问所有员工
     */
    public void accept(Visitor visitor) {
        System.out.println("\n🔍 访问者 [" + visitor.getClass().getSimpleName() + "] 正在访问公司员工...\n");
        for (Employee employee : employees) {
            employee.accept(visitor);
        }
    }
    
    /**
     * 获取员工数量
     */
    public int getEmployeeCount() {
        return employees.size();
    }
    
    /**
     * 获取公司名称
     */
    public String getName() {
        return name;
    }
    
    /**
     * 显示公司信息
     */
    public void showCompanyInfo() {
        System.out.println("\n🏢 公司: " + name);
        System.out.println("👥 员工数量: " + employees.size());
        System.out.println("员工列表:");
        for (Employee employee : employees) {
            System.out.println("  - " + employee.getName() + " (" + employee.getPosition() + ")");
        }
    }
}
