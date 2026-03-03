package com.skuu.springdesign.singleton;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

/**
 * Spring 单例：Bean 默认 SCOPE_SINGLETON，由容器保证唯一实例。
 */
@Configuration
public class AppConfig {

    @Bean
    @Scope(SCOPE_SINGLETON)
    public SharedService sharedService() {
        return new SharedService();
    }
}
