package com.example.conflitback.repository;


import com.example.conflitback.model.ERole;
import com.example.conflitback.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

    boolean existsByName(ERole name);
}
