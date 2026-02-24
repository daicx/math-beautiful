package com.skuu.design.prototype;

import lombok.Data;

/***
 * @Author dcx
 * @Description //原型模式
 * 原型模式是用于创建重复的对象，同时又能保证性能。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
 * @Date 17:08 2020/6/1
 * @Param
 * @return
 **/
@Data
public class UserPrototype implements Cloneable {
    private Integer id;
    private String name;
    private String desc;

    public UserPrototype() {
        System.out.println("创建对象成功...");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


}
