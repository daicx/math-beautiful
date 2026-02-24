package com.skuu.design.templatemethod.worker;

import com.skuu.design.templatemethod.Worker;

/**
 * @author dcx
 * @description
 * @create 2025-09-16 09:59
 **/
public class ITWorker extends Worker {
    public ITWorker(String name) {
        super(name);
    }

    @Override
    public boolean isNeedPrintDate() {
        return true;
    }

    @Override
    public void work() {
        System.out.println("--work ---" + name + ", 写程序 - 测bug - 修复bug");
    }
}
