package com.skuu.spi.javaspi1;

/**
 * TODO
 *
 * @author dcx
 * @since 2025-05-12 10:31
 **/
public class MysqlClient implements IClient{
    @Override
    public void open() {
        System.out.println("mysql open");
    }

    @Override
    public void close() {
        System.out.println("mysql close");
    }
}
