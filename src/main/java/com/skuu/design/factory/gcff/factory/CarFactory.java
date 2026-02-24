package com.skuu.design.factory.gcff.factory;

import com.skuu.design.factory.gcff.Product;
import com.skuu.design.factory.gcff.ProductAbstractFactory;
import com.skuu.design.factory.gcff.bo.Car;

/**
 * @author dcx
 * @description 6. 具体创建者 - 汽车工厂
 * @create 2025-09-12 17:06
 **/
public class CarFactory extends ProductAbstractFactory {
    @Override
    public Product createProduct() {
        return new Car();
    }
}
