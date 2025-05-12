package com.skuu.spi.javaspi2;

import com.skuu.spi.javaspi1.IClient;

/**
 * TODO
 *
 * @author dcx
 * @since 2025-05-12 10:31
 **/
public class OracleClient implements IClient {
    @Override
    public void open() {
        System.out.println("oracle open");
    }

    @Override
    public void close() {
        System.out.println("oracle close");
    }
}
