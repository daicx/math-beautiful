package com.skuu.springdesign.mediator;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * 中介者：通过 Spring ApplicationEventPublisher 转发消息，各 Colleague 用 @EventListener 响应。
 */
@Service
public class MediatorService {

    private final ApplicationEventPublisher publisher;

    public MediatorService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void broadcast(String message) {
        publisher.publishEvent(new MediatorEvent(message));
    }

    public static class MediatorEvent {
        private final String message;
        public MediatorEvent(String message) { this.message = message; }
        public String getMessage() { return message; }
    }
}
