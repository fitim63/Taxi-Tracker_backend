package com.ubt.app.controller;

import com.ubt.model.Vehicle;
import com.ubt.model.VehicleReport;
import com.ubt.service.DriverService;
import com.ubt.service.VehicleReportService;
import com.ubt.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    public static final Logger logger = LoggerFactory.getLogger(WorkScheduleController.class);
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleReportService vehicleReportService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Vehicle>> getDriverReports(){
        logger.info("List all vehicles");
        List<Vehicle> reports = vehicleService.getAll();
        if (reports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @RequestMapping(value = "/vehicle-reports", method = RequestMethod.GET)
    public ResponseEntity<List<VehicleReport>> getVehicleReports(){
        logger.info("List all vehicle reports");
        List<VehicleReport> reports = vehicleReportService.getAllVehicleReports();
        if (reports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }


}
