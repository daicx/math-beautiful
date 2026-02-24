package com.skuu.design.factory.cxgc.bo;

import com.skuu.design.factory.cxgc.OperatingSystem;

/**
 * 3. 具体产品A1 - Windows操作系统
 */
public class WindowsOS implements OperatingSystem {
    @Override
    public void boot() {
        System.out.println("Windows系统启动中...");
    }

    @Override
    public void shutdown() {
        System.out.println("Windows系统关闭中...");
    }

    @Override
    public String getName() {
        return "Windows";
    }
}
