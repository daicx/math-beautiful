package com.skuu.springdesign.facade;

import org.springframework.stereotype.Service;

/**
 * 外观：统一入口，委托给多个子组件（Spring 注入）。
 */
@Service
public class FacadeService {

    private final SubSystemA subA;
    private final SubSystemB subB;

    public FacadeService(SubSystemA subA, SubSystemB subB) {
        this.subA = subA;
        this.subB = subB;
    }

    public String doAll() {
        return subA.doA() + "-" + subB.doB();
    }
}
