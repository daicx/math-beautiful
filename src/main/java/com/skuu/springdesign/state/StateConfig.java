package com.skuu.springdesign.state;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.Map;

@Configuration
public class StateConfig {

    @Bean
    public Map<StateMachineService.State, Map<StateMachineService.Event, StateMachineService.State>> transitions() {
        Map<StateMachineService.State, Map<StateMachineService.Event, StateMachineService.State>> m =
            new EnumMap<>(StateMachineService.State.class);
        Map<StateMachineService.Event, StateMachineService.State> fromA =
            new EnumMap<>(StateMachineService.Event.class);
        fromA.put(StateMachineService.Event.E1, StateMachineService.State.B);
        m.put(StateMachineService.State.A, fromA);
        return m;
    }
}
