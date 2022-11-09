package com.skuu.design;

import lombok.Data;

/***
 * @Author dcx
 * @Description //TODO 原型模式
 * @Date 17:08 2020/6/1
 * @Param
 * @return
 **/
@Data
public class Prototype implements Cloneable {
    private String name;

    public Prototype() {
        System.out.println("创建对象成功...");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Prototype prototype = new Prototype();
        prototype.setName("本体");
        for (int i = 0; i < 10; i++) {
            Prototype clone = (Prototype) prototype.clone();
            System.out.println("对象创建成功---" + i + clone.getName());
        }

    }

}
