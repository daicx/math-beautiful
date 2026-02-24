package com.skuu.design.factory.cxgc;

/**
 * 7. 抽象工厂 - 定义创建产品的接口
 */
public interface SystemFactory {

    OperatingSystem createOperatingSystem();

    Browser createBrowser();

}
