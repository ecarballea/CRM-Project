package com.abaxconsulting.AbaxCRM.service;

import com.abaxconsulting.AbaxCRM.model.Contact;
import com.abaxconsulting.AbaxCRM.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact save(Contact contact) {
        Contact newContact =  contactRepository.save(contact);
        return newContact;
    }

    public Optional<Contact> getContact(UUID contactID) {
        Optional<Contact> existingContact = contactRepository.findById(contactID);
        return existingContact;

    }

    public Iterable<Contact> getAllContacts() {
        Iterable<Contact> contactsList = contactRepository.findAll();
        return contactsList;
    }

    public void delete(UUID id) {
        contactRepository.deleteById(id);
    }

    public int update(UUID contactID, Contact contact) {
        int result = contactRepository.updateNameAndPositionAndTypeById(
                contact.getName(), contact.getPosition(), contact.getType(), contactID);

        return result;
    }

}
