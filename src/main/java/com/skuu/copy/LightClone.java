package com.skuu.copy;


/**
 *  浅拷贝
 *
 * @author dcx
 * @since 2023-06-26 10:56
 **/

public class LightClone implements Cloneable{
    private User user;
    private String name;
    private int age;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "LightClone{" +
                "user=" + user +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
