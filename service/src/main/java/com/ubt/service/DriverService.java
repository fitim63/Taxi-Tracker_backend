package com.ubt.service;

import com.ubt.model.Driver;
import com.ubt.model.Driver;
import com.ubt.model.Server;
import com.ubt.repository.DriverRepository;
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
    private PasswordEncoder passwordEncoder;

    public List<Driver> getAll() {
        return driverRepository.findAll();
    }

    public Driver getById(int id) {
        return driverRepository.findById(id);
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
