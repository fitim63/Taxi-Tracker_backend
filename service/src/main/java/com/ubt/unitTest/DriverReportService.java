package com.ubt.service;

import com.ubt.model.DriverReport;
import com.ubt.repository.DriverReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverReportService {

    @Autowired
    private DriverReportRepository driverReportRepository;

    public List<DriverReport>  getAllDriverReports() {
        return driverReportRepository.findAll();
    }
}
