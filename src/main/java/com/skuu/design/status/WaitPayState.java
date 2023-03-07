package com.skuu.design.status;

import org.springframework.stereotype.Service;

/**
 *
 * @author dcx
 * @since 2023-02-27 17:31
 **/
@Service
public class WaitPayState implements State{

    @Override
    public void pay() {
        System.out.println("支付成功或者失败");
    }

    @Override
    public void deliver() {
        System.out.println("发货");
    }
}
