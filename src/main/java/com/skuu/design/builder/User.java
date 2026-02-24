package com.skuu.design.builder;

import lombok.Data;

/**
 * @author dcx
 * @description 建造者模式
 * @create 2025-09-16 18:32
 **/
@Data
public class User {
    private String name;
    private String desc;
    private Integer id;

    User(final String name, final String desc, final Integer id) {
        this.name = name;
        this.desc = desc;
        this.id = id;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }


    public static class UserBuilder {
        private String name;
        private String desc;
        private Integer id;

        UserBuilder() {
        }

        public UserBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public UserBuilder desc(final String desc) {
            this.desc = desc;
            return this;
        }

        public UserBuilder id(final Integer id) {
            this.id = id;
            return this;
        }

        public User build() {
            return new User(this.name, this.desc, this.id);
        }

        public String toString() {
            return "User.UserBuilder(name=" + this.name + ", desc=" + this.desc + ", id=" + this.id + ")";
        }
    }

}
