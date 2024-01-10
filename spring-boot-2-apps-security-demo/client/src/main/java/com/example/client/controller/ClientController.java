package com.example.client.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ClientController {
   private String secret = "javatechie";

   @PostMapping("/api/client")
   public void test() {
      RestTemplate restTemplate = new RestTemplate();
      HttpHeaders requestHeaders = new HttpHeaders();
      requestHeaders.add("Authorization", "Bearer " + generateToken("chaabinet"));
      HttpEntity<?> httpEntity = new HttpEntity<Object>(requestHeaders);
      ResponseEntity<String> responseEntity = restTemplate.exchange(
            "http://localhost:9091/api/server",
            HttpMethod.POST,
            httpEntity,
            String.class
      );
   }

   @GetMapping("/api/client/token")
   public void tesssst() {
      System.out.println("Bearer " + generateToken("chaabinet"));
   }

   public String generateToken(String username) {
      return Jwts
            .builder()
            .setClaims(new HashMap<>())
            .setSubject(username)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
   }

}
