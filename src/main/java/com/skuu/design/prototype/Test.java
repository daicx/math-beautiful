package com.skuu.design.prototype;

/**
 * @author dcx
 * @description
 * @create 2025-09-16 19:03
 **/
public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {
        UserPrototype userPrototype = new UserPrototype();
        userPrototype.setName("本体");
        for (int i = 0; i < 10; i++) {
            UserPrototype clone = (UserPrototype) userPrototype.clone();
            System.out.println("对象创建成功---" + i + clone.getName());
        }

    }
}
