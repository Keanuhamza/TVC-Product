package com.example.ASWS.controllers;

import java.util.List;

import com.example.ASWS.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

  @Autowired
  private ContactService contactService;

  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/contacts")
  List<Contact> all() {
    return contactService.getAllContacts();
  }
  // end::get-aggregate-root[]

  @PostMapping("/contact")
  Contact newContact(@RequestBody Contact newContact) {
    return contactService.addContact(newContact);
  }

  // Single item
  @GetMapping("/contact/{id}")
  Contact one(@PathVariable Long id) {
    return contactService.getContact(id);
  }

  @PutMapping("/contact/{id}")
  Contact replaceContact(@RequestBody Contact newContact, @PathVariable Long id) {
    return contactService.updateContact(newContact, id);
  }

  @DeleteMapping("/contact/{id}")
  void deleteContact(@PathVariable("id") Long id) {
    contactService.deleteContact(id);
  }
}
