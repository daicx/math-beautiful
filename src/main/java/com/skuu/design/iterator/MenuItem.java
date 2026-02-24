package com.skuu.design.iterator;

/**
 * @author dcx
 * @description 菜品类 - 迭代器模式的元素
 * @create 2025-01-27
 */
public class MenuItem {
    
    private String name;
    private String description;
    private boolean vegetarian;
    private double price;
    
    public MenuItem(String name, String description, boolean vegetarian, double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public boolean isVegetarian() {
        return vegetarian;
    }
    
    public double getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return String.format("%-20s ¥%.2f%s\n    %s", 
                           name, price, 
                           vegetarian ? " (素)" : "", 
                           description);
    }
}
