package com.skrtu.design;

import com.skrtu.design.dto.Client;

/***
 * @Author dcx
 * @Description //TODO 单例模式
 * @Date 16:49 2020/6/1
 * @Param
 * @return
 **/
public enum Single {
    INSTANCE;
    private Client client;

    Single() {
        this.client = new Client();
    }

    public Client getClient() {
        return client;
    }

    public static void main(String[] args) {
        Client client = Single.INSTANCE.getClient();
        Client client1 = Single.INSTANCE.getClient();
        System.out.println(client == client1);
    }
}
