package com.skuu.math;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class SkuuApplicationTests {

    @Test
    void contextLoads() {
        ArrayList<Integer> arr = new ArrayList<>();


    }
    public int singleNumber(int[] nums) {
        int num = 0;
        for(int i = 0; i < nums.length; i++){
            num = num ^ nums[i];
        }
        return num;
    }

    public void swap(int a, int b) {
        a = a + b;
        b = a - b;
        a = a - b;
    }

}
