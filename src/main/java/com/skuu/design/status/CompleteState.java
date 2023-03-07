package com.skuu.design.status;

import org.springframework.stereotype.Service;

/**
 *
 * @author dcx
 * @since 2023-02-27 17:31
 **/
@Service
public class CompleteState implements State{

    @Override
    public void pay() {
        System.out.println("已经完成");
    }

    @Override
    public void deliver() {
        System.out.println("已经完成");
    }
}
