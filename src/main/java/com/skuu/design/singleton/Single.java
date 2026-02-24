package com.skuu.design.singleton;

/***
 * @Author dcx
 * @Description 单例模式
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

}
