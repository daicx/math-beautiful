package com.skuu.design.iterator.menus;

import com.skuu.design.iterator.Aggregate;
import com.skuu.design.iterator.Iterator;
import com.skuu.design.iterator.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dcx
 * @description 早餐菜单 - 使用ArrayList存储
 * @create 2025-01-27
 */
public class BreakfastMenu implements Aggregate<MenuItem> {
    
    private List<MenuItem> menuItems;
    
    public BreakfastMenu() {
        menuItems = new ArrayList<>();
        
        // 初始化早餐菜单
        add(new MenuItem("素食早餐", "吐司配水煮蛋", true, 15.00));
        add(new MenuItem("传统早餐", "油条豆浆", true, 8.00));
        add(new MenuItem("西式早餐", "培根煎蛋三明治", false, 25.00));
        add(new MenuItem("养生粥", "小米南瓜粥", true, 12.00));
    }
    
    @Override
    public void add(MenuItem item) {
        menuItems.add(item);
    }
    
    @Override
    public Iterator<MenuItem> createIterator() {
        return new BreakfastMenuIterator();
    }
    
    @Override
    public int size() {
        return menuItems.size();
    }
    
    /**
     * 早餐菜单迭代器 - 使用List索引遍历
     */
    private class BreakfastMenuIterator implements Iterator<MenuItem> {
        private int position = 0;
        
        @Override
        public boolean hasNext() {
            return position < menuItems.size();
        }
        
        @Override
        public MenuItem next() {
            MenuItem item = menuItems.get(position);
            position++;
            return item;
        }
    }
}
