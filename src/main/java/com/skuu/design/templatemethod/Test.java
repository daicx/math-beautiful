package com.skuu.design.templatemethod;

import com.skuu.design.templatemethod.worker.*;

/**
 * @author dcx
 * @description
 * @create 2025-09-16 10:15
 **/
public class Test {
    public static void main(String[] args) {
        CTOWorker cto = new CTOWorker("CTO");
        cto.workOneDay();

        HRWorker hr = new HRWorker("HR");
        hr.workOneDay();

        ITWorker it = new ITWorker("IT");
        it.workOneDay();

        OtherWorker otherWorker = new OtherWorker("其他人员");
        otherWorker.workOneDay();

        QAWorker qa = new QAWorker("QA");
        qa.workOneDay();
    }
}
