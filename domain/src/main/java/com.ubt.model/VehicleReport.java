package com.ubt.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle_report", schema = "public")
public class VehicleReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicles;


    @Column(name = "distance_traveled")
    private double distanceTraveledInKilometers;

    @Column(name = "total_fuel_spent")
    private double totalFuelSpentUntilNow;


}
