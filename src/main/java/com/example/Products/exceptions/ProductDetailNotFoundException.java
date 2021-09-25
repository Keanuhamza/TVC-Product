package com.example.Products.exceptions;

public class ProductDetailNotFoundException extends RuntimeException {
    public ProductDetailNotFoundException(Long id) {
      super("Could not find product detail " + id);
    }
}
