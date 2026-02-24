package com.skuu.design.iterator.menus;

import com.skuu.design.iterator.Aggregate;
import com.skuu.design.iterator.Iterator;
import com.skuu.design.iterator.MenuItem;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dcx
 * @description 晚餐菜单 - 使用HashMap存储
 * @create 2025-01-27
 */
public class DinnerMenu implements Aggregate<MenuItem> {
    
    private Map<String, MenuItem> menuItems;
    
    public DinnerMenu() {
        menuItems = new HashMap<>();
        
        // 初始化晚餐菜单
        add(new MenuItem("龙虾套餐", "澳洲龙虾配黄油", false, 288.00));
        add(new MenuItem("牛排套餐", "安格斯牛排", false, 188.00));
        add(new MenuItem("海鲜大餐", "各式海鲜拼盘", false, 258.00));
        add(new MenuItem("素食套餐", "精选素食", true, 88.00));
    }
    
    @Override
    public void add(MenuItem item) {
        menuItems.put(item.getName(), item);
    }
    
    @Override
    public Iterator<MenuItem> createIterator() {
        return new DinnerMenuIterator();
    }
    
    @Override
    public int size() {
        return menuItems.size();
    }
    
    /**
     * 晚餐菜单迭代器 - 使用Map的values遍历
     */
    private class DinnerMenuIterator implements Iterator<MenuItem> {
        private java.util.Iterator<MenuItem> iterator;
        
        public DinnerMenuIterator() {
            iterator = menuItems.values().iterator();
        }
        
        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }
        
        @Override
        public MenuItem next() {
            return iterator.next();
        }
    }
}
