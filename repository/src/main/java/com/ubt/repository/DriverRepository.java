package com.ubt.repository;

import com.ubt.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

    Driver findByUsername(String username);

    Driver findById(int id);

}
