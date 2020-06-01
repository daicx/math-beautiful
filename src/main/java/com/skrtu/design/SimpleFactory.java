package com.skrtu.design;

import lombok.Data;

/***
 * @Author dcx
 * @Description //TODO 简单工厂模式
 * @Date 17:45 2020/6/1
 * @Param
 * @return
 **/
public class SimpleFactory {
    interface Fruit {
        void showName();
    }

    @Data
    static
    class Apple implements Fruit {

        @Override
        public void showName() {
            System.out.println("我是苹果");
        }
    }

    @Data
    static
    class Pear implements Fruit {

        @Override
        public void showName() {
            System.out.println("我是梨");
        }
    }

    static class Factory {
      static   Fruit newFruit(String type) {
            switch (type) {
                case "apple":
                    return new Apple();
                case "pear":
                    return new Pear();
                default:
                    return null;
            }
        }
    }

    public static void main(String[] args) {
        //生产一个苹果
        Fruit apple = Factory.newFruit("apple");
        apple.showName();
        Fruit pear = Factory.newFruit("pear");
        pear.showName();
    }
}
