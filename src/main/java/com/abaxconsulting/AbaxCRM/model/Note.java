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
@Table(name = "note", indexes = {
        @Index(name = "idx_note_id", columnList = "id")
})
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "note")
    private String note;

    @Column(name = "time_stamp")
    private Timestamp timeStamp;

    @ManyToMany(mappedBy = "notes")
    private List<Location> locations = new ArrayList<>();

    @ManyToMany(mappedBy = "notes")
    private List<Customer> customers = new ArrayList<>();

    @ManyToMany(mappedBy = "notes")
    private List<Contact> contacts = new ArrayList<>();

}