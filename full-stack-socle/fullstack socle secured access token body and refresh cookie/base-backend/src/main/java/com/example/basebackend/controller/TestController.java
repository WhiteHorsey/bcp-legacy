package com.example.basebackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "http://localhost:3000",
        exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"}, allowCredentials = "true", maxAge = 3600)
public class TestController {

    @GetMapping
    public String test() {
        return "hey";
    }
}
