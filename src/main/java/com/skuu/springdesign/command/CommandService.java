package com.skuu.springdesign.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 命令模式：Map<String, Runnable>，按名称执行。
 */
@Service
@RequiredArgsConstructor
public class CommandService {

    private final Map<String, Runnable> commands;

    public void execute(String name) {
        Runnable runnable = commands.get(name);
        if (runnable != null) runnable.run();
    }
}
