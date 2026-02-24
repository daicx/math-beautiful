package com.skuu.design.templatemethod.worker;

import com.skuu.design.templatemethod.Worker;

/**
 * @author dcx
 * @description
 * @create 2025-09-16 10:14
 **/
public class QAWorker extends Worker {
    public QAWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        System.out.println("--work ---" + name + ", 写测试用例 - 提交Bug - 复查Bug");
    }
}
