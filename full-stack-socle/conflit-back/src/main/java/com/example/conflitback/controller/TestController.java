package com.example.conflitback.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "http://localhost:3000",
//        exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"}, allowCredentials = "true", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public String test() {
        return "hey";
    }
}
