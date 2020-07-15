package com.ubt.app.controller;

import com.ubt.model.DriverWorkSchedule;
import com.ubt.service.WorkScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkScheduleController {

    public static final Logger logger = LoggerFactory.getLogger(WorkScheduleController.class);
    @Autowired
    private WorkScheduleService workScheduleService;

    @GetMapping("/driver-work-schedule")
    public ResponseEntity<List<DriverWorkSchedule>> getDriverReports(){
        logger.info("List all drivers' schedule");
        List<DriverWorkSchedule> reports = workScheduleService.getAll();
        if (reports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

}
