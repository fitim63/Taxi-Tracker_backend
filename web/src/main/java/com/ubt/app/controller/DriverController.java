package com.ubt.app.controller;

import com.ubt.app.util.Utils;
import com.ubt.model.Driver;
import com.ubt.model.DriverReport;
import com.ubt.model.VehicleReport;
import com.ubt.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class DriverController {

    public static final Logger logger = LoggerFactory.getLogger(DriverController.class);

    @Autowired
    private DriverService driverService;

    @GetMapping("/driver-reports")
    public ResponseEntity<List<DriverReport>> getDriverReports(){
        logger.info("List all drivers' reports");
        List<DriverReport> reports = driverService.getDriverReports();
        if (reports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @GetMapping("/vehicle-reports")
    public ResponseEntity<List<VehicleReport>> getVehicleReports(){
        logger.info("List all vehicle reports");
        List<VehicleReport> reports = driverService.getVehicleReports();
        if (reports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    // Get all users
    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> listAllUsers() {
        logger.info("List all drivers");
        List<Driver> drivers = driverService.getAll();
        if (drivers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    @GetMapping("/drivers/{id}")
    public ResponseEntity<Driver> getUser(@PathVariable ("id") int id) {
        logger.info("Get driver with id: "+id);
        // service + repository help web to provide data from database
        Driver driver = driverService.getById(id);
        if (driver == null) {
            logger.error("driver with id:"+id+" doesnt exist.");
        }
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }

    // Get driver by username
    @RequestMapping(value = "/drivers/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Driver> getDriverByUsername(@RequestParam ("username") String username) {
        logger.info("Get driver with username: "+username);
        // service + repository help web to provide data from database
        Driver driver = driverService.getDriverByUsername(username);
        if (driver == null) {
            logger.error("driver with id:"+username+" doesnt exist.");
        }
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }

    // create a driver
    @PostMapping("/createDriver")
    public ResponseEntity<?> createUser(@RequestBody Driver driver, UriComponentsBuilder uriCBuilder) {
        logger.info("Creating driver: {}", driver);

        if (driverService.getById(driver.getId()) != null) {
            logger.error("driver with id:"+ driver.getId()+" already exist.");
            return new ResponseEntity<>(new Utils
                    ("Unable to create driver with id:" + driver.getId() + " exist."),
                    HttpStatus.CONFLICT);
        }
        driverService.save(driver);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriCBuilder.path("/api/driver/{id}").buildAndExpand(driver.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/driver/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody Driver driver) {
        logger.info("Updating driver with id {}", id);
        Driver currentDriver = driverService.getById(id);

        if (currentDriver == null) {
            logger.error("Unable to update. Driver with id {} not found.", id);
            return new ResponseEntity<>(new Utils("Unable to update. Driver with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        currentDriver.setFirstName(driver.getFirstName());
        currentDriver.setLastName(driver.getLastName());
        currentDriver.setUsername(driver.getUsername());
        currentDriver.setPassword(driver.getPassword());
        driverService.save(currentDriver);
        return new ResponseEntity<>(currentDriver, HttpStatus.OK);
    }

    @DeleteMapping("/driver/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
        logger.info("Fetching & Deleting driver with id {}", id);
        Driver driver = driverService.getById(id);
        if (driver == null) {
            logger.error("Unable to delete. Driver with id {} not found.", id);
            return new ResponseEntity<>(new Utils("Unable to delete. Driver with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        driverService.deleteById(id);
        return new ResponseEntity<Driver>(HttpStatus.NO_CONTENT);
    }


}
