package com.skuu.copy;

/**
 * 测试拷贝
 *
 * @author dcx
 * @since 2023-06-26 10:59
 **/
public class Test {

    /**
     * 浅拷贝
     * 1.属性为值类型，进行值传递。属性为引用类型，进行引用传递。
     * @param
     * @return void
     * @author dcx
     * @date 2023/6/26 11:18
     **/
    public static void lightClone() throws CloneNotSupportedException {
        User user = new User(1, "dd");
        User user1 = new User(2, "dd2");
        LightClone lightClone = new LightClone();
        lightClone.setAge(10);
        lightClone.setName("dd10");
        lightClone.setUser(user);
        System.out.println("原始对象："+lightClone);
        LightClone clone = (LightClone) lightClone.clone();
        System.out.println("克隆的对象："+clone);
        clone.setName("dd2");
        clone.setAge(3);
        clone.setUser(user1);
        System.out.println("克隆的对象修改为："+clone);
        System.out.println("修改克隆对象后，原始对象属性："+lightClone);
        lightClone.setName("dd3");
        lightClone.setAge(4);
        lightClone.setUser(user);
        System.out.println("修改原始对象为："+lightClone);
        System.out.println("修改原始对象后，克隆对象属性："+clone);
        System.out.println("--------测试修改克隆的引用");
        LightClone lightClone1 = new LightClone();
        lightClone1.setAge(20);
        lightClone1.setName("dd20");
        lightClone1.setUser(user);
        LightClone clone1 = (LightClone) lightClone1.clone();
        user.setAge(2222);
        user.setName("2222");
        System.out.println(lightClone1);
        System.out.println(clone1);
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        lightClone();
    }
}
