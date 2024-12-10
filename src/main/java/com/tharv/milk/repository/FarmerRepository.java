package com.tharv.milk.repository;

import com.tharv.milk.domain.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Integer> {
    // Custom query methods can be added here
}