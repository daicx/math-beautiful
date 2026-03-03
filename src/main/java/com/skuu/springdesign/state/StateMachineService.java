package com.skuu.springdesign.state;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 状态模式：枚举 + Map<State, Map<Event, State>> 转换表。
 */
@Service
@RequiredArgsConstructor
public class StateMachineService {

    private final Map<State, Map<Event, State>> transitions;

    public State next(State current, Event event) {
        Map<Event, State> map = transitions.get(current);
        return map != null && map.containsKey(event) ? map.get(event) : current;
    }

    public enum State { A, B, C }
    public enum Event { E1, E2 }
}
