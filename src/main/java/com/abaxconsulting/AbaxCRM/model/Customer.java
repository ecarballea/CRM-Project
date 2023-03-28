package com.abaxconsulting.AbaxCRM.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@Table(name = "customer", indexes = {
        @Index(name = "idx_customer_id_industry_name", columnList = "id, industry, name, status")
})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "industry")
    private String industry;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "tax_rate")
    private Double taxRate;

    @Column(name = "time_stamp")
    private Timestamp timeStamp;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REFRESH, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Location> locations = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REFRESH, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Contact> contacts = new ArrayList<>();


    @OneToMany(mappedBy = "customer", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Note> notes = new ArrayList<>();

}