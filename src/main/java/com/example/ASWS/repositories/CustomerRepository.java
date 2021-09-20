package com.example.ASWS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ASWS.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}