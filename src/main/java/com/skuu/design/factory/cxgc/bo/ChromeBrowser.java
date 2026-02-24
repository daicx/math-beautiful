package com.skuu.design.factory.cxgc.bo;

import com.skuu.design.factory.cxgc.Browser;

/**
 * 5. 具体产品B1 - Chrome浏览器
 */
public class ChromeBrowser implements Browser {
    @Override
    public void open() {
        System.out.println("Chrome浏览器打开中...");
    }

    @Override
    public void close() {
        System.out.println("Chrome浏览器关闭中...");
    }

    @Override
    public String getName() {
        return "Chrome";
    }
}
