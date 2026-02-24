package com.skuu.design.templatemethod.worker;

import com.skuu.design.templatemethod.Worker;

/**
 * @author dcx
 * @description
 * @create 2025-09-16 09:56
 **/
public class CTOWorker extends Worker {
    public CTOWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        System.out.println("--work ---" + name + ", 开会 - 出API - 检查进度");
    }
}
