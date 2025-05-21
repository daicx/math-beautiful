package com.skuu.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * bean的通知
 *
 * @author dcx
 * @since 2025-05-21 10:37
 **/
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("beanAware")){
            System.out.println("执行初始化前置方法：BeanAware");
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("beanAware")) {
            System.out.println("执行初始化后置方法：BeanAware");
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
