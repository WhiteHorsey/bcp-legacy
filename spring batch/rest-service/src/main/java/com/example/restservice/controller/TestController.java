package com.example.restservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private int count = 0;

    @GetMapping("/api/enrich")
    public String enrich() {
        this.count++;
        return String.format("Enriched %s", this.count);
    }
}
