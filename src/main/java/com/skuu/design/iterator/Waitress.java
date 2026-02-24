package com.skuu.design.iterator;

/**
 * @author dcx
 * @description 服务员类 - 使用迭代器遍历菜单
 * @create 2025-01-27
 */
public class Waitress {
    
    private Aggregate<MenuItem> breakfastMenu;
    private Aggregate<MenuItem> lunchMenu;
    private Aggregate<MenuItem> dinnerMenu;
    
    public Waitress(Aggregate<MenuItem> breakfastMenu, 
                   Aggregate<MenuItem> lunchMenu, 
                   Aggregate<MenuItem> dinnerMenu) {
        this.breakfastMenu = breakfastMenu;
        this.lunchMenu = lunchMenu;
        this.dinnerMenu = dinnerMenu;
    }
    
    /**
     * 打印所有菜单
     */
    public void printMenu() {
        System.out.println("📋 ========== 餐厅菜单 ==========\n");
        
        System.out.println("🌅 早餐菜单:");
        printMenu(breakfastMenu.createIterator());
        
        System.out.println("\n🌞 午餐菜单:");
        printMenu(lunchMenu.createIterator());
        
        System.out.println("\n🌙 晚餐菜单:");
        printMenu(dinnerMenu.createIterator());
    }
    
    /**
     * 打印单个菜单
     */
    private void printMenu(Iterator<MenuItem> iterator) {
        while (iterator.hasNext()) {
            MenuItem item = iterator.next();
            System.out.println("  " + item);
        }
    }
    
    /**
     * 打印素食菜单
     */
    public void printVegetarianMenu() {
        System.out.println("\n🥬 ========== 素食菜单 ==========\n");
        
        System.out.println("🌅 早餐:");
        printVegetarianMenu(breakfastMenu.createIterator());
        
        System.out.println("\n🌞 午餐:");
        printVegetarianMenu(lunchMenu.createIterator());
        
        System.out.println("\n🌙 晚餐:");
        printVegetarianMenu(dinnerMenu.createIterator());
    }
    
    /**
     * 打印素食菜单项
     */
    private void printVegetarianMenu(Iterator<MenuItem> iterator) {
        while (iterator.hasNext()) {
            MenuItem item = iterator.next();
            if (item.isVegetarian()) {
                System.out.println("  " + item);
            }
        }
    }
    
    /**
     * 检查是否有某个菜品
     */
    public boolean isItemVegetarian(String name) {
        Iterator<MenuItem> iterator = breakfastMenu.createIterator();
        if (isItemVegetarian(iterator, name)) {
            return true;
        }
        
        iterator = lunchMenu.createIterator();
        if (isItemVegetarian(iterator, name)) {
            return true;
        }
        
        iterator = dinnerMenu.createIterator();
        return isItemVegetarian(iterator, name);
    }
    
    private boolean isItemVegetarian(Iterator<MenuItem> iterator, String name) {
        while (iterator.hasNext()) {
            MenuItem item = iterator.next();
            if (item.getName().equals(name)) {
                return item.isVegetarian();
            }
        }
        return false;
    }
}
