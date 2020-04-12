package com.ubt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "work_schedule", schema = "public")
public class DriverWorkSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "started_at")
    private Date driverWorkStartTime;

    @Column(name = "finish_at")
    private Date driverWorkEndTime;


    @Column(name = "is_working")
    private Double is_working;


}
