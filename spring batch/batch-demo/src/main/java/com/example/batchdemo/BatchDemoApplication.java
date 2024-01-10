package com.example.batchdemo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
@EnableBatchProcessing
public class BatchDemoApplication {

    public static void main(String[] args) {

        List<String> fullArgs = new ArrayList<>(args.length + 1);
        fullArgs.add("timestamp = " + new Date().getTime());
        fullArgs.addAll(Arrays.asList(args));

        Properties properties = System.getProperties();
        properties.put("spring.profiles.active", "master");

        SpringApplication.run(BatchDemoApplication.class, fullArgs.toArray(new String[0]));
    }

}
