package com.skuu.design.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dcx
 * @description 文件夹类 - 组合模式的Composite
 * @create 2025-01-27
 */
public class Folder implements FileSystemComponent {
    
    private String name;
    private List<FileSystemComponent> children;
    
    public Folder(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public long getSize() {
        long totalSize = 0;
        for (FileSystemComponent component : children) {
            totalSize += component.getSize();
        }
        return totalSize;
    }
    
    @Override
    public void display(String prefix) {
        System.out.println(prefix + "📁 " + name + " (" + formatSize(getSize()) + ")");
        for (FileSystemComponent component : children) {
            component.display(prefix + "  ");
        }
    }
    
    @Override
    public void add(FileSystemComponent component) {
        children.add(component);
    }
    
    @Override
    public void remove(FileSystemComponent component) {
        children.remove(component);
    }
    
    @Override
    public FileSystemComponent getChild(int index) {
        if (index >= 0 && index < children.size()) {
            return children.get(index);
        }
        return null;
    }
    
    @Override
    public boolean isContainer() {
        return true;
    }
    
    /**
     * 获取子组件数量
     */
    public int getChildCount() {
        return children.size();
    }
    
    /**
     * 格式化文件大小显示
     */
    private String formatSize(long size) {
        if (size < 1024) {
            return size + " B";
        } else if (size < 1024 * 1024) {
            return String.format("%.1f KB", size / 1024.0);
        } else if (size < 1024 * 1024 * 1024) {
            return String.format("%.1f MB", size / (1024.0 * 1024.0));
        } else {
            return String.format("%.1f GB", size / (1024.0 * 1024.0 * 1024.0));
        }
    }
}
