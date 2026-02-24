package com.skuu.design.composite;

/**
 * @author dcx
 * @description 文件类 - 组合模式的Leaf
 * @create 2025-01-27
 */
public class File implements FileSystemComponent {
    
    private String name;
    private long size;
    
    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public long getSize() {
        return size;
    }
    
    @Override
    public void display(String prefix) {
        System.out.println(prefix + "📄 " + name + " (" + formatSize(size) + ")");
    }
    
    @Override
    public boolean isContainer() {
        return false;
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
