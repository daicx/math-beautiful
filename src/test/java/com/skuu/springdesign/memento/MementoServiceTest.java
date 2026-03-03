package com.skuu.springdesign.memento;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 备忘录模式用法：save(Supplier 取状态) 得到 Memento，restore(m, Consumer 写状态) 恢复。
 */
@SpringBootTest
class MementoServiceTest {

    @Autowired
    private MementoService mementoService;

    @Test
    void saveAndRestore() {
        StringBuilder state = new StringBuilder("original");
        MementoService.Memento m = mementoService.save(state::toString);
        state.setLength(0);
        state.append("changed");
        mementoService.restore(m, s -> { state.setLength(0); state.append(s); });
        assertEquals("original", state.toString());
    }
}
