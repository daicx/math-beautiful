package com.skuu.design.iterator.menus;

import com.skuu.design.iterator.Aggregate;
import com.skuu.design.iterator.Iterator;
import com.skuu.design.iterator.MenuItem;

/**
 * @author dcx
 * @description 午餐菜单 - 使用数组存储
 * @create 2025-01-27
 */
public class LunchMenu implements Aggregate<MenuItem> {
    
    private static final int MAX_ITEMS = 6;
    private MenuItem[] menuItems;
    private int numberOfItems = 0;
    
    public LunchMenu() {
        menuItems = new MenuItem[MAX_ITEMS];
        
        // 初始化午餐菜单
        add(new MenuItem("宫保鸡丁", "经典川菜", false, 38.00));
        add(new MenuItem("鱼香肉丝", "酸辣可口", false, 35.00));
        add(new MenuItem("麻婆豆腐", "麻辣鲜香", true, 28.00));
        add(new MenuItem("红烧肉", "肥而不腻", false, 45.00));
        add(new MenuItem("清炒时蔬", "健康营养", true, 18.00));
    }
    
    @Override
    public void add(MenuItem item) {
        if (numberOfItems >= MAX_ITEMS) {
            System.err.println("菜单已满，无法添加更多菜品");
        } else {
            menuItems[numberOfItems] = item;
            numberOfItems++;
        }
    }
    
    @Override
    public Iterator<MenuItem> createIterator() {
        return new LunchMenuIterator();
    }
    
    @Override
    public int size() {
        return numberOfItems;
    }
    
    /**
     * 午餐菜单迭代器 - 使用数组索引遍历
     */
    private class LunchMenuIterator implements Iterator<MenuItem> {
        private int position = 0;
        
        @Override
        public boolean hasNext() {
            return position < numberOfItems && menuItems[position] != null;
        }
        
        @Override
        public MenuItem next() {
            MenuItem item = menuItems[position];
            position++;
            return item;
        }
    }
}
