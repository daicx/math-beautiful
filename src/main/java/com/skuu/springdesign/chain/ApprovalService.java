package com.skuu.springdesign.chain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ApprovalService {

    private final List<Function<LeaveRequest, Optional<ApprovalResult>>> chain;

    public ApprovalResult handle(LeaveRequest request) {
        return chain.stream()
            .map(f -> f.apply(request))
            .filter(Optional::isPresent)
            .findFirst()
            .flatMap(Function.identity())
            .orElse(new ApprovalResult(false, "Rejected"));
    }
}
