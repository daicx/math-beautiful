package com.skuu.design.factory.gcff.factory;

import com.skuu.design.factory.gcff.Product;
import com.skuu.design.factory.gcff.ProductAbstractFactory;
import com.skuu.design.factory.gcff.bo.Bicycle;

/**
 * @author dcx
 * @description 8. 具体创建者 - 自行车工厂
 * @create 2025-09-12 17:09
 **/
public class BicycleFactory extends ProductAbstractFactory {
    @Override
    public Product createProduct() {
        return new Bicycle();
    }
}
