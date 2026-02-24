package com.skuu.comm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.math.BigDecimal;

/**
 * TODO
 *
 * @author dcx
 * @since 2022-11-21 23:32
 **/
public class Test {

    @Data
    public static class A {
        private BigDecimal a;
    }

    public static void main(String[] args) throws JsonProcessingException {
        System.out.println(34000000001330L%32);
    }

}
