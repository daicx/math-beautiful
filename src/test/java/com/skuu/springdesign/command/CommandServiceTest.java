package com.skuu.springdesign.command;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 命令模式用法：按名称从 Map&lt;String, Runnable&gt; 执行对应命令。
 */
@SpringBootTest
class CommandServiceTest {

    @Autowired
    private CommandService commandService;

    @Test
    void executeCommand() {
        commandService.execute("lightOn");
        commandService.execute("lightOff");
    }
}
