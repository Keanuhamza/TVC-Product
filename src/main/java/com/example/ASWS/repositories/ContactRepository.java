package com.example.ASWS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ASWS.models.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}