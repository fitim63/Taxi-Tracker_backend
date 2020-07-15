/*
package com.ubt.app.controller;

import com.ubt.model.VehicleDrivingDetails;
import com.ubt.service.VehicleDrivingDetailsService;
import dto.VehicleDrivingDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
public class DriverDetailsController {

    @Autowired
    private VehicleDrivingDetailsService vehicleDrivingDetailsService;

    @GetMapping("/vehicle-details/{id}")
    public ResponseEntity<VehicleDrivingDetails> getDriverDetails(@PathVariable ("id") int id) {
        Optional<VehicleDrivingDetails> vehicleDrivingDetails = vehicleDrivingDetailsService.getVehicleDetailsById(id);
        return vehicleDrivingDetails.map(drivingDetails -> new ResponseEntity<>(drivingDetails, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PostMapping("/driving-vehicle-details")
    public ResponseEntity<VehicleDrivingDetailsDto> getDriverDrivingDetails(@RequestBody VehicleDrivingDetailsDto vdd) {
        if (vdd != null) {
            VehicleDrivingDetails vehicleDrivingDetails = new VehicleDrivingDetails();
            vehicleDrivingDetails.setLatitude(vdd.getLatitude());
            vehicleDrivingDetails.setLongitude(vdd.getLongitude());
            vehicleDrivingDetails.setVehicle(vdd.getDriverCarId());

        }
        Optional<VehicleDrivingDetailsDto> vehicleDrivingDetails = vehicleDrivingDetailsService.getVehicleDetailsById(id);
        return vehicleDrivingDetails.map(drivingDetails -> new ResponseEntity<>(drivingDetails, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }


    @GetMapping("/driving-vehicle-details/{id}")
    public ResponseEntity<VehicleDrivingDetailsDto> getDriverDrivingDetails(@PathVariable ("id") int id) {
        Optional<VehicleDrivingDetailsDto> vehicleDrivingDetails = vehicleDrivingDetailsService.getVehicleDetailsById(id);
        return vehicleDrivingDetails.map(drivingDetails -> new ResponseEntity<>(drivingDetails, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }
}
*/
