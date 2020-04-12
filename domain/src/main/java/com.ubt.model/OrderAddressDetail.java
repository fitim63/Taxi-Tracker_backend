package com.ubt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_address", schema = "public")
public class OrderAddressDetail{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "from_address")
    private String clientCurrentAddress;

    @Column(name = "to_destination")
    private String clientDestinationAddress;

    @Column(name = "zip_code")
    private int zipcode;

}
