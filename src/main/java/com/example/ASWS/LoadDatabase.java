package com.example.ASWS;

import com.example.ASWS.models.*;
import com.example.ASWS.repositories.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean @Order
  CommandLineRunner initDatabase(CustomerRepository repository, ContactRepository repository2) {

    return args -> {
      log.info("Preloading " + repository.save(new Customer(1L, "Nasa", "33 Evergreen NSW 3088", "Australia")));
      log.info("Preloading " + repository2.save(new Contact(1L, "Lucifer", 630450028989L, "kh784@uowmail.edu.au", "CEO")));
    };
  } 

}