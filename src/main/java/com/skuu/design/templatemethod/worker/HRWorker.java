package com.skuu.design.templatemethod.worker;

import com.skuu.design.templatemethod.Worker;

/**
 * @author dcx
 * @description
 * @create 2025-09-16 09:58
 **/
public class HRWorker extends Worker {
    public HRWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        System.out.println("--work ---" + name + ", 看简历 - 打电话 - 接电话");
    }
}
