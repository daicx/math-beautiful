package com.skuu.design.status;

import lombok.Data;

/**
 *  订单上下文
 *
 * @author dcx
 * @since 2023-02-27 17:04
 **/
@Data
public class OrderContext {
    public static  final WaitPayState WAIT_PAY_STATE =  new WaitPayState();
    public static  final WaitCompleteState WAIT_COMPLETE_STATE =  new WaitCompleteState();
    public static  final CompleteState COMPLETE_STATE =  new CompleteState();

    private State curState;

    public void pay(){
        curState.pay();
    }

    public void deliver(){
        curState.deliver();
    }
}
