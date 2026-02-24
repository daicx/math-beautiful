package com.skuu.design.factory.gcff.factory;

import com.skuu.design.factory.gcff.Product;
import com.skuu.design.factory.gcff.ProductAbstractFactory;
import com.skuu.design.factory.gcff.bo.Motorcycle;

/**
 * @author dcx
 * @description 7. 具体创建者 - 摩托车工厂
 * @create 2025-09-12 17:08
 **/
public class MotorcycleFactory extends ProductAbstractFactory {
    @Override
    public Product createProduct() {
        return new Motorcycle();
    }
}
