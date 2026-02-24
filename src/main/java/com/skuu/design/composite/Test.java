package com.skuu.design.composite;

/**
 * @author dcx
 * @description 组合模式测试类 - 文件系统示例
 * @create 2025-01-27
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("=== 组合模式 - 文件系统示例 ===\n");

        // 创建根目录
        Folder root = new Folder("项目根目录");
        
        // 创建文档文件夹
        Folder documents = new Folder("文档");
        documents.add(new File("需求文档.docx", 2 * 1024 * 1024)); // 2MB
        documents.add(new File("设计文档.pdf", 5 * 1024 * 1024));  // 5MB
        documents.add(new File("API文档.md", 512 * 1024));         // 512KB
        
        // 创建源代码文件夹
        Folder sourceCode = new Folder("源代码");
        
        // Java源代码子文件夹
        Folder javaCode = new Folder("java");
        javaCode.add(new File("Main.java", 1024));           // 1KB
        javaCode.add(new File("Utils.java", 2048));          // 2KB
        javaCode.add(new File("Service.java", 3072));        // 3KB
        
        // 配置文件子文件夹
        Folder config = new Folder("config");
        config.add(new File("application.properties", 512)); // 512B
        config.add(new File("logback.xml", 1024));           // 1KB
        
        // 将子文件夹添加到源代码文件夹
        sourceCode.add(javaCode);
        sourceCode.add(config);
        
        // 创建资源文件夹
        Folder resources = new Folder("resources");
        resources.add(new File("logo.png", 256 * 1024));     // 256KB
        resources.add(new File("banner.jpg", 1 * 1024 * 1024)); // 1MB
        
        // 创建测试文件夹
        Folder tests = new Folder("测试");
        tests.add(new File("UnitTest.java", 1536));          // 1.5KB
        tests.add(new File("IntegrationTest.java", 2048));   // 2KB
        
        // 将主要文件夹添加到根目录
        root.add(documents);
        root.add(sourceCode);
        root.add(resources);
        root.add(tests);
        
        // 添加一些根目录下的直接文件
        root.add(new File("README.md", 1024));               // 1KB
        root.add(new File("pom.xml", 2048));                 // 2KB
        
        // 显示整个文件系统结构
        System.out.println("文件系统结构：");
        root.display("");
        
        System.out.println("\n=== 组合模式操作演示 ===");
        
        // 演示统一操作 - 计算总大小
        System.out.println("项目总大小: " + formatSize(root.getSize()));
        System.out.println("文档文件夹大小: " + formatSize(documents.getSize()));
        System.out.println("源代码文件夹大小: " + formatSize(sourceCode.getSize()));
        
        // 演示动态添加文件
        System.out.println("\n--- 动态添加文件 ---");
        FileSystemComponent newFile = new File("新增文件.txt", 1024);
        documents.add(newFile);
        System.out.println("添加新文件后，文档文件夹大小: " + formatSize(documents.getSize()));
        
        // 演示删除文件
        System.out.println("\n--- 删除文件 ---");
        documents.remove(newFile);
        System.out.println("删除文件后，文档文件夹大小: " + formatSize(documents.getSize()));
        
        // 演示遍历子组件
        System.out.println("\n--- 遍历子组件 ---");
        System.out.println("根目录下的子组件数量: " + root.getChildCount());
        for (int i = 0; i < root.getChildCount(); i++) {
            FileSystemComponent child = root.getChild(i);
            System.out.println("  " + (i + 1) + ". " + child.getName() + 
                             " (" + (child.isContainer() ? "文件夹" : "文件") + ")");
        }
        
        System.out.println("\n=== 组合模式特点总结 ===");
        System.out.println("1. 统一接口: 文件和文件夹都实现FileSystemComponent接口");
        System.out.println("2. 递归结构: 文件夹可以包含文件和子文件夹");
        System.out.println("3. 透明性: 客户端可以统一处理文件和文件夹");
        System.out.println("4. 扩展性: 易于添加新的文件系统组件类型");
    }
    
    /**
     * 格式化文件大小显示
     */
    private static String formatSize(long size) {
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
