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
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/customers")
  List<Customer> all() {
    return customerService.getAllCustomers();
  }
  // end::get-aggregate-root[]

  @PostMapping("/customer")
  Customer newContact(@RequestBody Customer newCustomer) {
    return customerService.addCustomer(newCustomer);
  }

  // Single item
  @GetMapping("/customer/{id}")
  Customer one(@PathVariable Long id) {
    return customerService.getCustomer(id); }

  @PutMapping("/customer/{id}")
  Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {

    return customerService.updateCustomer(newCustomer, id);
  }

  @PutMapping("/customer/{id}/contact/{contactid}")
  Customer updateCustomerContact(@PathVariable Long id, @PathVariable Long contactid) {
    return customerService.updateCustomerContact(id, contactid);
  }

  @DeleteMapping("/customer/{id}")
  void deleteCustomer(@PathVariable("id") Long id) {
    customerService.deleteCustomer(id);
  }
}
