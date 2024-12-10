package com.tharv.milk.repository;

import com.tharv.milk.domain.Centre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentreRepository extends JpaRepository<Centre, Integer> {
    // Custom query methods can be added here
}