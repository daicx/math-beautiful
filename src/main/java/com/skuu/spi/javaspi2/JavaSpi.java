package com.skuu.spi.javaspi2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ServiceLoader;

/**
 * TODO
 *
 * @author dcx
 * @since 2025-05-12 11:09
 **/
public class JavaSpi {

    /**
     * 主程序接口，提供IClient。外部jar包，引入IClient，并且提供IClient的接口实现。
     * 同时在META-INF/services 目录下增加以接口全限定名命名的文件。
     * 主程序通过classloader加载外部jar包，ServiceLoader找到其实现。
     * @param args
     * @return void
     * @author dcx
     * @date 2025/5/12 15:48
     **/
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String jarPath = "/Users/dcx/me/math-beautiful/target/math-beauiful-0.0.1-SNAPSHOT.jar";
        File file = new File(jarPath);
        System.out.println(file.exists());
        URL[] urls = new URL[]{file.toURI().toURL()};
        try (URLClassLoader urlClassLoader = new URLClassLoader(urls, Thread.currentThread().getContextClassLoader())) {
            ServiceLoader<IClient> load = ServiceLoader.load(IClient.class, urlClassLoader);
            for (IClient iClient : load) {
                iClient.open();
            }
        }


    }
}
