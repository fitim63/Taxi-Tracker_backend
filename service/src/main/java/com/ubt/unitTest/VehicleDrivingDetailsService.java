package com.ubt.service;

import com.ubt.model.VehicleDrivingDetails;
import com.ubt.repository.VehicleDrivingDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleDrivingDetailsService {

    @Autowired
    private VehicleDrivingDetailsRepository vehicleDriverDetailsRepository;

    public Optional<VehicleDrivingDetails> getVehicleDetailsById(int id){
        return vehicleDriverDetailsRepository.findById(id);
    }
}
