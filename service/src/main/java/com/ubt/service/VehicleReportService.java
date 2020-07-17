package com.ubt.service;

import com.ubt.model.DriverReport;
import com.ubt.model.VehicleReport;
import com.ubt.repository.DriverReportRepository;
import com.ubt.repository.VehicleReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleReportService {

    @Autowired
    private VehicleReportRepository vehicleReportRepository;

    public List<VehicleReport> getAllVehicleReports() {
        return vehicleReportRepository.findAll();
    }
}
