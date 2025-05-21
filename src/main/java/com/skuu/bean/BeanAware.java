package com.skuu.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author dcx
 * @since 2025-05-21 10:31
 **/
@Component
public class BeanAware implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, ResourceLoaderAware {

    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;
    private ResourceLoader resourceLoader;

    @Override
    public void setBeanName(String s) {
        System.out.println("收到通知：" + s);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("收到通知：beanFactory");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("收到通知：applicationContext");
        this.applicationContext = applicationContext;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("收到通知：resourceLoader");
        this.resourceLoader = resourceLoader;
    }

    /**
     * bean初始化
     * @param
     * @return void
     * @author dcx
     * @date 2025/5/21 11:10
     **/
    @PostConstruct
    public void postConstruct() {
        System.out.println("执行bean的初始化方法：BeanAware");
    }

    public void use() {
        System.out.println("使用 Bean：BeanAware");
    }

    /**
     * bean销毁
     * @param
     * @return void
     * @author dcx
     * @date 2025/5/21 11:10
     **/
    @PreDestroy
    public void preDestroy() {
        System.out.println("bean被销毁：BeanAware");
    }

}
