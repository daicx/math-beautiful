package com.skuu.design.iterator;

import com.skuu.design.iterator.menus.*;

/**
 * @author dcx
 * @description 迭代器模式测试类
 * @create 2025-01-27
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("=== 迭代器模式 - 餐厅菜单示例 ===\n");

        // 创建不同的菜单（使用不同的数据结构）
        Aggregate<MenuItem> breakfastMenu = new BreakfastMenu();  // 使用ArrayList
        Aggregate<MenuItem> lunchMenu = new LunchMenu();          // 使用数组
        Aggregate<MenuItem> dinnerMenu = new DinnerMenu();        // 使用HashMap

        System.out.println("📝 菜单信息:");
        System.out.println("  早餐菜单: 使用ArrayList存储，共" + breakfastMenu.size() + "道菜");
        System.out.println("  午餐菜单: 使用数组存储，共" + lunchMenu.size() + "道菜");
        System.out.println("  晚餐菜单: 使用HashMap存储，共" + dinnerMenu.size() + "道菜");

        // 创建服务员
        Waitress waitress = new Waitress(breakfastMenu, lunchMenu, dinnerMenu);

        // ========== 场景1：打印所有菜单 ==========
        System.out.println("\n【场景1：打印所有菜单】");
        System.out.println("服务员不需要知道菜单的内部实现，统一使用迭代器遍历\n");
        waitress.printMenu();

        // ========== 场景2：打印素食菜单 ==========
        System.out.println("\n\n【场景2：打印素食菜单】");
        System.out.println("通过迭代器过滤素食菜品\n");
        waitress.printVegetarianMenu();

        // ========== 场景3：查询特定菜品 ==========
        System.out.println("\n\n【场景3：查询特定菜品】");
        String[] dishesToCheck = {"麻婆豆腐", "宫保鸡丁", "清炒时蔬", "红烧肉"};
        
        for (String dish : dishesToCheck) {
            boolean isVeg = waitress.isItemVegetarian(dish);
            System.out.println("  \"" + dish + "\" 是素食? " + (isVeg ? "✅ 是" : "❌ 否"));
        }

        // ========== 场景4：演示迭代器的独立性 ==========
        System.out.println("\n\n【场景4：演示迭代器的独立性】");
        System.out.println("可以同时创建多个迭代器，互不影响\n");
        
        Iterator<MenuItem> iterator1 = breakfastMenu.createIterator();
        Iterator<MenuItem> iterator2 = breakfastMenu.createIterator();
        
        System.out.println("迭代器1遍历早餐菜单:");
        int count1 = 0;
        while (iterator1.hasNext()) {
            MenuItem item = iterator1.next();
            count1++;
            System.out.println("  " + count1 + ". " + item.getName());
        }
        
        System.out.println("\n迭代器2独立遍历早餐菜单:");
        int count2 = 0;
        while (iterator2.hasNext()) {
            MenuItem item = iterator2.next();
            count2++;
            System.out.println("  " + count2 + ". " + item.getName());
        }

        // ========== 对比不使用迭代器 ==========
        System.out.println("\n\n【对比：不使用迭代器模式】");
        System.out.println("\n❌ 不使用迭代器:");
        System.out.println("  - 需要知道每个菜单的内部实现（ArrayList、数组、HashMap）");
        System.out.println("  - 遍历代码不统一，需要针对每种数据结构编写不同代码");
        System.out.println("  - 代码耦合度高，难以维护和扩展");
        
        System.out.println("\n✅ 使用迭代器:");
        System.out.println("  - 统一的遍历接口，不需要知道内部实现");
        System.out.println("  - 遍历代码统一，易于维护");
        System.out.println("  - 低耦合，可以轻松添加新的菜单类型");

        // ========== 总结 ==========
        System.out.println("\n\n=== 迭代器模式说明 ===");
        System.out.println("1. 迭代器接口: Iterator - 定义遍历方法");
        System.out.println("2. 聚合接口: Aggregate - 定义创建迭代器的方法");
        System.out.println("3. 具体聚合: BreakfastMenu等 - 实现具体的数据结构");
        System.out.println("4. 具体迭代器: 内部类 - 实现具体的遍历逻辑");
        System.out.println("5. 客户端: Waitress - 使用迭代器统一遍历");

        System.out.println("\n=== 迭代器模式优势 ===");
        System.out.println("✅ 统一接口: 提供统一的遍历方式");
        System.out.println("✅ 封装性: 隐藏聚合对象的内部结构");
        System.out.println("✅ 单一职责: 遍历逻辑与数据结构分离");
        System.out.println("✅ 多种遍历: 可以为同一聚合提供多种遍历方式");
        System.out.println("✅ 简化聚合接口: 聚合对象不需要提供遍历方法");

        System.out.println("\n=== 迭代器模式应用场景 ===");
        System.out.println("📌 集合遍历: Java集合框架的Iterator");
        System.out.println("📌 树形结构: 遍历树、图等复杂结构");
        System.out.println("📌 分页查询: 数据库结果集的遍历");
        System.out.println("📌 文件系统: 遍历文件和目录");
        System.out.println("📌 网络数据: 流式数据的遍历");

        System.out.println("\n=== Java中的迭代器模式 ===");
        System.out.println("🔸 java.util.Iterator: Java标准迭代器接口");
        System.out.println("🔸 java.util.Iterable: 可迭代接口");
        System.out.println("🔸 for-each循环: 基于迭代器实现");
        System.out.println("🔸 Stream API: 增强的迭代器");
        System.out.println("🔸 ResultSet: JDBC结果集迭代器");

        System.out.println("\n=== 迭代器模式关键点 ===");
        System.out.println("🔑 分离遍历行为: 将遍历逻辑从聚合对象中分离");
        System.out.println("🔑 统一接口: 所有迭代器实现相同接口");
        System.out.println("🔑 内部迭代器: 通常作为聚合对象的内部类");
        System.out.println("🔑 多游标: 可以同时存在多个迭代器");
    }
}
