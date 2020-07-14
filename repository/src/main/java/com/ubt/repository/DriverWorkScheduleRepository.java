package com.ubt.repository;

import com.ubt.model.DriverWorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverWorkScheduleRepository extends JpaRepository<DriverWorkSchedule, Integer> {

    Optional<DriverWorkSchedule> findById(int id);

}
