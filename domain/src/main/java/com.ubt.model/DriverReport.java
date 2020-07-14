package com.ubt.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "driver_report", schema = "public")
public class DriverReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonFormat(pattern = "dd-MM-yyyy", timezone="Europe/Zagreb")
    @Column(name = "created_at")
    private Date createdAt;

    @JsonFormat(pattern = "dd-MM-yyyy", timezone="Europe/Zagreb")
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Column(name = "total_average_speed")
    private Double driverAverageSpeed;

}
