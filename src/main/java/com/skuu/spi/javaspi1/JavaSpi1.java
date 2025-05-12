package com.skuu.spi.javaspi1;

import java.util.ServiceLoader;

/**
 * @author dcx
 * @since 2025-05-12 10:29
 **/
public class JavaSpi1 {
    //ServiceLoader.load(Class<T> service) 方法加载服务时，
    // 会检查 META-INF/services 目录下是否存在以接口全限定名命名的文件。
    // 如果存在，则读取文件内容，获取实现该接口的类的全限定名，并通过 Class.forName() 方法加载对应的类。
    public static void main(String[] args) {
        ServiceLoader<IClient> load = ServiceLoader.load(IClient.class);
        for (IClient iClient : load) {
            iClient.open();
        }
    }
}
