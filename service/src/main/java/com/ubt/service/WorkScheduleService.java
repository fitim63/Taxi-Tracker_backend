package com.ubt.service;

import com.ubt.model.DriverWorkSchedule;
import com.ubt.repository.DriverWorkScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class WorkScheduleService {

    @Autowired
    private DriverWorkScheduleRepository driverWorkScheduleRepository;


    public List<DriverWorkSchedule> getAll() {
        return driverWorkScheduleRepository.findAll();
    }

    public Optional<DriverWorkSchedule> getById(int id) {
        return driverWorkScheduleRepository.findById(id);
    }

    public void deleteById(int id) {
        driverWorkScheduleRepository.deleteById(id);
    }


    public void save(DriverWorkSchedule driverWorkSchedule) {
        driverWorkScheduleRepository.save(driverWorkSchedule);
    }


}