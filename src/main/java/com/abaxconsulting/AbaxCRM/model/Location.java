package com.abaxconsulting.AbaxCRM.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "location", indexes = {
        @Index(name = "idx_location_address_city", columnList = "address, city, country, name, state, name_address_state_city, zip, id")
})
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "name")
    private String name;


    @Column(name = "state")
    private String state;

    @Column(name = "tax_rate")
    private Double taxRate;

    @Column(name = "zip")
    private String zip;

    @Column(name = "name_address_state_city")
    private String nameAddressStateCity;

    @Column(name = "time_stamp")
    private Timestamp timeStamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "location", cascade = CascadeType.REFRESH, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "location_notes",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "notes_id"))
    private List<Note> notes = new ArrayList<>();

}