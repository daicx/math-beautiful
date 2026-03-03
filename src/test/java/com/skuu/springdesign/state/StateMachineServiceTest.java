package com.skuu.springdesign.state;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 状态模式用法：枚举 + 转换表 Map&lt;State, Map&lt;Event, State&gt;&gt;，next(current, event) 得到下一状态。
 */
@SpringBootTest
class StateMachineServiceTest {

    @Autowired
    private StateMachineService stateMachineService;

    @Test
    void stateTransition() {
        StateMachineService.State next = stateMachineService.next(
            StateMachineService.State.A, StateMachineService.Event.E1);
        assertEquals(StateMachineService.State.B, next);
    }
}
