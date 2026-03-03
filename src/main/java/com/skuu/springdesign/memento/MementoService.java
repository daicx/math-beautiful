package com.skuu.springdesign.memento;

import org.springframework.stereotype.Service;

import java.util.function.Supplier;
import java.util.function.Consumer;

/**
 * 备忘录：状态不可变，save 返回 Memento，restore 接受 Memento（Supplier/Consumer 封装）。
 */
@Service
public class MementoService {

    public static class Memento {
        private final String state;
        public Memento(String state) { this.state = state; }
        public String getState() { return state; }
    }

    public Memento save(Supplier<String> getState) {
        return new Memento(getState.get());
    }

    public void restore(Memento m, Consumer<String> setState) {
        if (m != null) setState.accept(m.getState());
    }
}
