package com.skuu.design;

/***
 * @Author dcx
 * @Description //TODO 工厂模式
 * 分别创造苹果和梨
 * @Date 17:24 2020/6/1
 * @Param
 * @return
 **/
public class Factory {
    interface Fruit {
        void showFruit();
    }

    static class Apple implements Fruit {

        @Override
        public void showFruit() {
            System.out.println("我是苹果");
        }
    }

    static class Pear implements Fruit {

        @Override
        public void showFruit() {
            System.out.println("我是梨");
        }
    }

    interface AbstractFactory {
        Fruit newFruit();
    }

    static class AppleFactory implements AbstractFactory {

        @Override
        public Fruit newFruit() {
            System.out.println("生产一个苹果.");
            return new Apple();
        }
    }

    static class PearFactory implements AbstractFactory {

        @Override
        public Fruit newFruit() {
            System.out.println("生产一个梨.");
            return new Pear();
        }
    }

    public static void main(String[] args) {
        //生产一个苹果
        AbstractFactory abstractFactory = new AppleFactory();
        Fruit fruit = abstractFactory.newFruit();
        fruit.showFruit();
        //生产一个梨
        AbstractFactory abstractFactory1 = new PearFactory();
        Fruit fruit1 = abstractFactory1.newFruit();
        fruit1.showFruit();

    }

}
