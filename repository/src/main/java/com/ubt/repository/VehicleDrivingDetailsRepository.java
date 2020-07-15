package com.ubt.repository;

import com.ubt.model.VehicleDrivingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface VehicleDrivingDetailsRepository extends JpaRepository<VehicleDrivingDetails, Integer> {

    Optional<VehicleDrivingDetails> getVehicleDrivingDetailsById(int id);
}
