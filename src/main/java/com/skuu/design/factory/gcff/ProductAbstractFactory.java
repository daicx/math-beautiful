package com.skuu.design.factory.gcff;

/**
 * @author dcx
 * @description 5. 创建者抽象类 - 定义工厂方法
 * @create 2025-09-12 17:01
 **/
public abstract class ProductAbstractFactory {
    /**
     * 工厂方法 - 由子类实现具体的创建逻辑
     */
    public abstract Product createProduct();

    /**
     * 模板方法 - 使用工厂方法创建产品并执行操作
     */
    public void createAndUseProduct() {
        Product product = createProduct();
        product.start();
        product.drive();
        product.stop();
        System.out.println(product.getType() + "制造完成！\n");
    }
}
