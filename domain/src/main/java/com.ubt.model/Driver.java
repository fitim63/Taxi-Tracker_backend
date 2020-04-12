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


    @OneToMany(mappedBy = "driver")
    private List<Order> order;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_schedule_id")
    private DriverWorkSchedule driverWorkSchedule;


    @Column(name = "date_of_birth")
    private int dateOfBirth;

}
