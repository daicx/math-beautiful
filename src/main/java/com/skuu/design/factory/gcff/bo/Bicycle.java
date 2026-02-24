package com.skuu.design.factory.gcff.bo;

import com.skuu.design.factory.gcff.Product;

/**
 * @author dcx
 * @description 4. 具体产品 - 自行车
 * @create 2025-09-12 16:58
 **/
public class Bicycle implements Product {
    @Override
    public void start() {
        System.out.println("自行车启动：踩踏板开始骑行");
    }

    @Override
    public void stop() {
        System.out.println("自行车停止：停止踩踏板，使用刹车");
    }

    @Override
    public void drive() {
        System.out.println("自行车行驶：持续踩踏板，控制方向");
    }

    @Override
    public String getType() {
        return "自行车";
    }
}
