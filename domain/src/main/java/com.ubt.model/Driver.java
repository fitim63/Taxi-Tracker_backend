package com.ubt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "driver", schema = "public")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private int age;

    @Column(name = "user_uuid")
    private UUID uuid;

    @OneToMany(mappedBy = "driver")
    private List<Order> order;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "vehicle_driver",
            joinColumns = @JoinColumn(name = "driver_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id", referencedColumnName = "id"))
    private Set<Vehicle> vehicles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_schedule_id")
    private DriverWorkSchedule driverWorkSchedule;


    @Column(name = "date_of_birth")
    private int dateOfBirth;


    public Driver(String firstName, String lastName, String username, String password, int age, UUID uuid, List<Order> orders,
                   DriverWorkSchedule driverWorkSchedule, int dateOfBirth, Vehicle... vehicles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.age = age;
        this.uuid = uuid;
        this.order = orders;
        this.vehicles = Stream.of(vehicles).collect(Collectors.toSet());
        this.vehicles.forEach(x -> x.getDriver().add(this));
    }
}
