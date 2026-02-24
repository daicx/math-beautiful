package com.skuu.protection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DataControllerTest {

    @Autowired
    private DataController dataController;

    @Test
    void getData() throws JsonProcessingException {
        DataResponse data = dataController.getData("11");
        System.out.println(new ObjectMapper().writeValueAsString(data));
    }
}