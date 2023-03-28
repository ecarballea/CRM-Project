package com.abaxconsulting.AbaxCRM.repository;

import com.abaxconsulting.AbaxCRM.model.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {
    @Transactional
    @Modifying
    @Query("update Customer c set c.industry = ?1, c.name = ?2, c.status = ?3, c.taxRate = ?4 where c.id = ?5")
    int updateIndustryAndNameAndStatusAndTaxRateById(String industry, String name, String status, Double taxRate, UUID id);
}