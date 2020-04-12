package com.ubt.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order", schema = "public")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "server_id")
    private Server server;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Column(name = "amount")
    private Double amount;

    @OneToOne
    @JoinColumn(name = "order_address_id")
    private OrderAddressDetail orderAddressDetail;

    @Column(name = "is_payed")
    private Boolean isPayed;

}
