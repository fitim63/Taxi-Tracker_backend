package com.ubt.repository;

import com.ubt.model.DriverReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverReportRepository extends JpaRepository<DriverReport, Integer> {
}
