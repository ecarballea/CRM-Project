package com.abaxconsulting.AbaxCRM.repository;

import com.abaxconsulting.AbaxCRM.model.Location;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface LocationRepository extends CrudRepository<Location, UUID> {
    @Transactional
    @Modifying
    @Query("""
            update Location l set l.address = ?1, l.city = ?2, l.country = ?3, l.name = ?4, l.state = ?5, l.taxRate = ?6, l.zip = ?7
            where l.id = ?8""")
    int updateAddressAndCityAndCountryAndNameAndStateAndTaxRateAndZipById(String address, String city, String country, String name, String state, Double taxRate, String zip, UUID id);
}