package com.skuu.proxy;

/**
 *
 * @author dcx
 * @since 2023-06-21 09:57
 **/
public interface OrderService {
    void create(int id);
    void pay(int id);
}
