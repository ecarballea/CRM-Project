package com.abaxconsulting.AbaxCRM.repository;

import com.abaxconsulting.AbaxCRM.model.Contact;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface ContactRepository extends CrudRepository<Contact, UUID> {
    @Transactional
    @Modifying
    @Query("update Contact c set c.name = ?1, c.position = ?2, c.type = ?3 where c.id = ?4")
    int updateNameAndPositionAndTypeById(String name, String position, String type, UUID id);
}