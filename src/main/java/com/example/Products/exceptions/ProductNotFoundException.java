package com.example.Product.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
      super("Could not find product " + id);
    }
    public ProductNotFoundException(String productName) {
      super("Could not find product " + productName);
    }
  }
