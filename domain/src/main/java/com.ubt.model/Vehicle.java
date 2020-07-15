package com.ubt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicle", schema = "public")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "model")
    private String model;

    @Column(name = "fuel_type")
    private String fuelType;

    @OneToMany
    private List<VehicleDrivingDetails> drivingDetails;

    @ManyToMany(mappedBy = "vehicles")
    private List<Driver> driver;

    @Column(name = "status")
    private boolean currentStatus;

}
