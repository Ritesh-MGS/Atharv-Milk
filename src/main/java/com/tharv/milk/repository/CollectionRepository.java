package com.tharv.milk.repository;

import com.tharv.milk.domain.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Integer> {
    // Custom query methods can be added here
    List<Collection> findByMilkType(String milkType);

    // Custom query to find collections by farmer's id
    List<Collection> findByFarmerId(int farmerId);
}