package com.skuu.design.factory.jdgc;

/**
* @description 
* @author dcx
* @create 2025-09-12 16:47
**/
public class Test {
    public static void main(String[] args) {
        SimpleFactory simpleFactory = new SimpleFactory();
        Shape circle = simpleFactory.createShape("circle");
        circle.draw();
    }
}
