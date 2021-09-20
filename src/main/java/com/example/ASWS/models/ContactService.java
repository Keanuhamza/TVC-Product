package com.example.ASWS.models;
import com.example.ASWS.repositories.*;
import com.example.ASWS.exceptions.*;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ContactService {
    
    private ContactRepository repository;

    ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    public Contact addContact(Contact contact) {   
        return repository.save(contact);
    }

    public Contact getContact(Long id) {   
        return repository.findById(id).orElseThrow(() -> new ContactNotFoundException(id));
  
    }

    public List<Contact> getAllContacts() {   
        return repository.findAll();
    }

    public Contact updateContact(Contact newContact, Long id) {
        return repository.findById(id)
        .map(contact -> {
        contact.setName(newContact.getName());
        contact.setPhone(newContact.getPhone());
        contact.setEmail(newContact.getEmail());
        contact.setPosition(newContact.getPosition());
        return repository.save(contact);
        })
        .orElseGet(() -> {
        newContact.setId(id);
        return repository.save(newContact);
        });
    }

    public void deleteContact(Long id) {   
        repository.deleteById(id);
    }

}
