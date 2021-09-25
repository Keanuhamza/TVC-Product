package com.example.Product;

import com.example.Product.models.*;
import com.example.Product.repositories.*;

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
  CommandLineRunner initDatabase(ProductRepository repository, ProductDetailRepository repository2) {
    return args -> {
      log.info("Preloading " + repository.save(new Product(1L, "Tools", "Hammer", 50f, 10)));
      log.info("Preloading " + repository2.save(new ProductDetail(1L, "Steel copper alloy", "Very popular")));
    };
  } 

}