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
  CommandLineRunner initDatabase(ProductRepository repository, ProductDetailRepository repository2) {
    return args -> {
      log.info("Preloading " + repository.save(new Product(1L, "Tools", "Hammer", 50f, 100)));
      log.info("Preloading " + repository2.save(new ProductDetail(1L, "Steel copper alloy", "Very popular")));

      log.info("Preloading " + repository.save(new Product(2L, "Tools", "Spanner", 25f, 150)));
      log.info("Preloading " + repository2.save(new ProductDetail(2L, "Steel alloy", "Very popular")));

      log.info("Preloading " + repository.save(new Product(3L, "Food", "Cake", 31.50f, 190)));
      log.info("Preloading " + repository2.save(new ProductDetail(3L, "Sweet", "Very popular")));

      log.info("Preloading " + repository.save(new Product(4L, "Food", "Apple", 12.58f, 80)));
      log.info("Preloading " + repository2.save(new ProductDetail(4L, "Red Fruit", "Very popular")));

      log.info("Preloading " + repository.save(new Product(5L, "Food", "Orange", 96.10f, 79)));
      log.info("Preloading " + repository2.save(new ProductDetail(5L, "Orange Fruit", "Very popular")));

    };
  } 

}