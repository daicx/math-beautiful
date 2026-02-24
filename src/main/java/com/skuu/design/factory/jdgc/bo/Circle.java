package com.skuu.design.factory.jdgc.bo;

import com.skuu.design.factory.jdgc.Shape;

/**
 * @author dcx
 * @description 具体产品
 * @create 2025-09-12 16:45
 **/
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("画圆形");
    }
}
