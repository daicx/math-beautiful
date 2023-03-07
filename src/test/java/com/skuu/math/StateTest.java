package com.skuu.math;

import com.skuu.design.status.OrderContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 状态模式测试
 *
 * @author dcx
 * @since 2023-02-27 20:41
 **/
@SpringBootTest
public class StateTest {

    @Test
    public void exec() {
        new OrderContext();

    }
}
