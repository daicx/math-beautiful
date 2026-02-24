package com.skuu.protection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dcx
 * @description
 * @create 2025-12-01 18:41
 **/
@RestController
@RequestMapping("/api")
public class DataController {

    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/data")
    public DataResponse getData(@RequestParam String param) {
        return dataService.getDataFromExternal(param);
    }
}
