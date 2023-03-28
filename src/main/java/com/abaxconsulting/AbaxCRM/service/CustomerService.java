package com.abaxconsulting.AbaxCRM.service;

import com.abaxconsulting.AbaxCRM.model.Customer;
import com.abaxconsulting.AbaxCRM.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        Customer newCustomer =  customerRepository.save(customer);
        return newCustomer;
    }

    public Optional<Customer> getCustomer(UUID customerID) {
        Optional<Customer> existingCustomer = customerRepository.findById(customerID);
        return existingCustomer;

    }

    public Iterable<Customer> getAllCustomers() {
        Iterable<Customer> customersList = customerRepository.findAll();
        return customersList;
    }

    public void delete(UUID id) {
        customerRepository.deleteById(id);
    }
}
