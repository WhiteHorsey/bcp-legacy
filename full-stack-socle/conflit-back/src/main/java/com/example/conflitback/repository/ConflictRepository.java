package com.example.conflitback.repository;

import com.example.conflitback.model.Conflict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConflictRepository extends JpaRepository<Conflict, Long> {
}
