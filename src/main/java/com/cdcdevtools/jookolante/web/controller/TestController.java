package com.cdcdevtools.jookolante.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @Value("${spring.datasource.url}")
    private String datasourceUrl;
    
    @GetMapping("/api/test/config")
    public String testConfig() {
        return "Datasource URL: " + datasourceUrl;
    }
}