package com.example.conflitback.repository;

import com.example.conflitback.model.Opponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpponentRepository extends JpaRepository<Opponent, Long> {

}
