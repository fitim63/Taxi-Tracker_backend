package com.ubt.repository;

import com.ubt.model.VehicleDrivingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDrivingDetailsRepository extends JpaRepository<VehicleDrivingDetails, Integer> {
}
