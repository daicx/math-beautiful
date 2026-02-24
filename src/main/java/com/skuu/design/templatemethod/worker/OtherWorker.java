package com.skuu.design.templatemethod.worker;

import com.skuu.design.templatemethod.Worker;

/**
 * @author dcx
 * @description
 * @create 2025-09-16 10:00
 **/
public class OtherWorker extends Worker {
    public OtherWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        System.out.println("--work ---" + name + ",打LOL...");
    }
}
