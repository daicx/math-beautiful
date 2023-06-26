package com.skuu.copy;

import lombok.Data;

/**
 *
 * @author dcx
 * @since 2023-06-26 10:57
 **/
@Data
public class User {
    private int age;
    private String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
