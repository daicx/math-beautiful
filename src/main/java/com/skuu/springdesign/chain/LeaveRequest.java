package com.skuu.springdesign.chain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LeaveRequest {
    private String person;
    private int days;
}
