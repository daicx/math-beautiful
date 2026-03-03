package com.skuu.springdesign.chain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApprovalResult {
    private boolean approved;
    private String handlerName;
}
