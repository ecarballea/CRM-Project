package com.abaxconsulting.AbaxCRM.controller;

import com.abaxconsulting.AbaxCRM.exception.CustomerNotFoundException;
import com.abaxconsulting.AbaxCRM.model.Customer;
import com.abaxconsulting.AbaxCRM.service.CustomerService;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@CrossOrigin
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("")
    public ResponseEntity<?> newCustomer(@RequestBody @Valid @Nonnull Customer customer){
        if (customer == null) {
            throw new IllegalArgumentException("Customer is missing");
        } else{
            Customer newCustomer = customerService.save(customer);
            return new ResponseEntity<Customer>(newCustomer, HttpStatus.CREATED);
        }
    }

    @GetMapping("/{customerID}")
    public ResponseEntity<?> getCustomer(@Valid @Nonnull @PathVariable UUID customerID){
        Optional<Customer> existingCustomer = customerService.getCustomer(customerID);
        if (existingCustomer.isEmpty()){
            throw new CustomerNotFoundException("The Customer ID '"+customerID+"' does not exist");
        } else {
            return new ResponseEntity<Customer>(existingCustomer.get(), HttpStatus.OK);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAllCustomers(){
        Iterable<Customer> customersList = customerService.getAllCustomers();
        if (((ArrayList) customersList).size() == 0){
            throw new CustomerNotFoundException("There are not any Customer yet");
        } else {
            return new ResponseEntity<Iterable<Customer>>(customersList, HttpStatus.OK);
        }
    }

    @PatchMapping("/{customerID}")
    public ResponseEntity<?> updateCustomer(@RequestBody @Valid @Nonnull Customer customer,
                                            @Valid @Nonnull @PathVariable UUID customerID){
        if (customer == null) {
            throw new IllegalArgumentException("Customer is missing");
        } else if (customerID == null) {
            throw new IllegalArgumentException("Customer ID is missing");
        } else {
            Optional<Customer> existingCustomer = customerService.getCustomer(customerID);
            if (existingCustomer.isEmpty()){
                throw new CustomerNotFoundException("The Customer ID '"+customerID+"' does not exist");
            } else {
                int response = customerService.update(customerID, customer);
                return new ResponseEntity<Customer>(HttpStatus.OK);
            }
        }
    }

    @DeleteMapping("/{customerID}")
    public ResponseEntity<?> deleteCustomer(@Valid @Nonnull @PathVariable UUID customerID){
        if (customerID == null) {
            throw new IllegalArgumentException("Customer ID is missing");
        } else {
            Optional<Customer> existingCustomer = customerService.getCustomer(customerID);
            if (existingCustomer.isEmpty()){
                throw new CustomerNotFoundException("The Customer ID '"+customerID+"' does not exist");
            } else {
                customerService.delete(existingCustomer.get().getId());
                return new ResponseEntity<Customer>(HttpStatus.OK);
            }
        }
    }


}
