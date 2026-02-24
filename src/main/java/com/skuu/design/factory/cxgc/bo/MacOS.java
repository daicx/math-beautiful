package com.skuu.design.factory.cxgc.bo;

import com.skuu.design.factory.cxgc.OperatingSystem;

/**
 * 4. 具体产品A2 - macOS操作系统
 */
public class MacOS implements OperatingSystem {
    @Override
    public void boot() {
        System.out.println("macOS系统启动中...");
    }

    @Override
    public void shutdown() {
        System.out.println("macOS系统关闭中...");
    }

    @Override
    public String getName() {
        return "macOS";
    }
}
