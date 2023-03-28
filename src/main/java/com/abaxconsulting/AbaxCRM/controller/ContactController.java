package com.abaxconsulting.AbaxCRM.controller;

import com.abaxconsulting.AbaxCRM.exception.ContactNotFoundException;
import com.abaxconsulting.AbaxCRM.model.Contact;
import com.abaxconsulting.AbaxCRM.service.ContactService;
import com.abaxconsulting.AbaxCRM.service.ContactService;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("")
    public ResponseEntity<?> newContact(@RequestBody @Valid @Nonnull Contact contact){
        if (contact == null) {
            throw new IllegalArgumentException("Contact is missing");
        } else{
            Contact newContact = contactService.save(contact);
            return new ResponseEntity<Contact>(newContact, HttpStatus.CREATED);
        }
    }

    @GetMapping("/{contactID}")
    public ResponseEntity<?> getContact(@Valid @Nonnull @PathVariable UUID contactID){
        Optional<Contact> existingContact = contactService.getContact(contactID);
        if (existingContact.isEmpty()){
            throw new ContactNotFoundException("The Contact ID '"+contactID+"' does not exist");
        } else {
            return new ResponseEntity<Contact>(existingContact.get(), HttpStatus.OK);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAllContacts(){
        Iterable<Contact> contactsList = contactService.getAllContacts();
        if (((ArrayList) contactsList).size() == 0){
            throw new ContactNotFoundException("There are not any Contact yet");
        } else {
            return new ResponseEntity<Iterable<Contact>>(contactsList, HttpStatus.OK);
        }
    }

    @PatchMapping("/{contactID}")
    public ResponseEntity<?> updateContact(@RequestBody @Valid @Nonnull Contact contact,
                                            @Valid @Nonnull @PathVariable UUID contactID){
        if (contact == null) {
            throw new IllegalArgumentException("Contact is missing");
        } else if (contactID == null) {
            throw new IllegalArgumentException("Contact ID is missing");
        } else {
            Optional<Contact> existingContact = contactService.getContact(contactID);
            if (existingContact.isEmpty()){
                throw new ContactNotFoundException("The Contact ID '"+contactID+"' does not exist");
            } else {
                int response = contactService.update(contactID, contact);
                return new ResponseEntity<Contact>(HttpStatus.OK);
            }
        }
    }

    @DeleteMapping("/{contactID}")
    public ResponseEntity<?> deleteContact(@Valid @Nonnull @PathVariable UUID contactID){
        if (contactID == null) {
            throw new IllegalArgumentException("Contact ID is missing");
        } else {
            Optional<Contact> existingContact = contactService.getContact(contactID);
            if (existingContact.isEmpty()){
                throw new ContactNotFoundException("The Contact ID '"+contactID+"' does not exist");
            } else {
                contactService.delete(existingContact.get().getId());
                return new ResponseEntity<Contact>(HttpStatus.OK);
            }
        }
    }

}
