package com.skuu.design.factory.cxgc;

/**
 * 10. 客户端 - 使用抽象工厂
 */
public class ComputerSystem {
    private OperatingSystem os;
    private Browser browser;

    public ComputerSystem(SystemFactory factory) {
        this.os = factory.createOperatingSystem();
        this.browser = factory.createBrowser();
    }

    public void startSystem() {
        System.out.println("=== 启动计算机系统 ===");
        os.boot();
        browser.open();
        System.out.println("系统启动完成！\n");
    }

    public void shutdownSystem() {
        System.out.println("=== 关闭计算机系统 ===");
        browser.close();
        os.shutdown();
        System.out.println("系统关闭完成！\n");
    }

    public void showSystemInfo() {
        System.out.println("系统信息：");
        System.out.println("操作系统：" + os.getName());
        System.out.println("浏览器：" + browser.getName() + "\n");
    }
}
