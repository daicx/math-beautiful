package com.skuu.design.factory.cxgc.bo;

import com.skuu.design.factory.cxgc.Browser;

/**
 * 6. 具体产品B2 - Safari浏览器
 */
public class SafariBrowser implements Browser {
    @Override
    public void open() {
        System.out.println("Safari浏览器打开中...");
    }

    @Override
    public void close() {
        System.out.println("Safari浏览器关闭中...");
    }

    @Override
    public String getName() {
        return "Safari";
    }
}
