package com.skuu.springdesign.combination;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

/**
 * 组合模式（业务）：事件发布 + 校验链（Predicate）+ 策略（按 key 选逻辑），综合 Spring Events、责任链、策略。
 */
@Service
@RequiredArgsConstructor
public class CombinationService {

    private final ApplicationEventPublisher publisher;


    public boolean validate(Object request, java.util.List<Predicate<Object>> validators) {
        return validators.stream().allMatch(v -> v.test(request));
    }

    public void publish(Object event) {
        publisher.publishEvent(event);
    }
}
