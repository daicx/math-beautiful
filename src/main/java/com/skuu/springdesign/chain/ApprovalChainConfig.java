package com.skuu.springdesign.chain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * 责任链：链为 List<Function<LeaveRequest, Optional<ApprovalResult>>>，顺序执行直到某步返回 non-empty。
 */
@Configuration
public class ApprovalChainConfig {

    @Bean
    public List<Function<LeaveRequest, Optional<ApprovalResult>>> approvalChain() {
        return Arrays.asList(
            req -> req.getDays() <= 1 ? Optional.of(new ApprovalResult(true, "TeamLeader")) : Optional.empty(),
            req -> req.getDays() <= 3 ? Optional.of(new ApprovalResult(true, "DeptManager")) : Optional.empty(),
            req -> req.getDays() <= 7 ? Optional.of(new ApprovalResult(true, "GeneralManager")) : Optional.empty(),
            req -> Optional.of(new ApprovalResult(false, "Rejected"))
        );
    }
}
