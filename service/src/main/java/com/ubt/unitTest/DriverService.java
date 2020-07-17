package com.ubt.unitTest;

import com.ubt.model.*;
import com.ubt.model.Driver;
import com.ubt.repository.DriverReportRepository;
import com.ubt.repository.DriverRepository;
import com.ubt.repository.VehicleReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("Duplicates")
@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private DriverReportRepository driverReportRepository;

    @Autowired
    private VehicleReportRepository vehicleReportRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<DriverReport> getDriverReports() {
        return driverReportRepository.findAll();
    }

    public List<VehicleReport> getVehicleReports() {
        return vehicleReportRepository.findAll();
    }

    public List<Driver> getAll() {
        return driverRepository.findAll();
    }

    public Driver getById(int id) {
        return driverRepository.findById(id);
    }

    public Driver getDriverByUsername(String username) {
        return driverRepository.findByUsername(username);
    }

    public void deleteById(int id) {
        driverRepository.deleteById(id);
    }

    public Driver getByUsername(String username) {
        return driverRepository.findByUsername(username);
    }

    public void save(Driver u) {
        Driver driver = new Driver();
        if (u.getUuid() == null) {
            u.setUuid(UUID.randomUUID());
        }
        driver.setUuid(u.getUuid());
        driver.setFirstName(u.getFirstName());
        driver.setLastName(u.getLastName());
        driver.setPassword(u.getPassword());
        driver.setPassword(passwordEncoder.encode(u.getPassword()));
        driver.setUsername(u.getUsername());
        driverRepository.save(driver);
    }
}
