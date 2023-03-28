package com.abaxconsulting.AbaxCRM.repository;

import com.abaxconsulting.AbaxCRM.model.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ContactRepository extends CrudRepository<Contact, UUID> {
}