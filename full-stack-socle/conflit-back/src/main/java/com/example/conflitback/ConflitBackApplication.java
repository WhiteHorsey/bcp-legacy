package com.example.conflitback;

import com.example.conflitback.model.Role;
import com.example.conflitback.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.example.conflitback.model.ERole.ADMIN;
import static com.example.conflitback.model.ERole.USER;

@SpringBootApplication
public class ConflitBackApplication implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(ConflitBackApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (!roleRepository.existsByName(USER)) {
            roleRepository.save(new Role(USER));
        }
        if (!roleRepository.existsByName(ADMIN)) {
            roleRepository.save(new Role(ADMIN));
        }
    }
}
