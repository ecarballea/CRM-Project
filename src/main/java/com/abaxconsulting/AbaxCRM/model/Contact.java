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
@Table(name = "contact", indexes = {
        @Index(name = "idx_contact_id_name_position", columnList = "id, name, position, type")
})
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private String position;

    @Column(name = "type")
    private String type;

    @Column(name = "time_stamp")
    private Timestamp timeStamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    @JsonIgnore
    private Location location;


    @OneToMany(mappedBy = "contact", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Note> notes = new ArrayList<>();

}