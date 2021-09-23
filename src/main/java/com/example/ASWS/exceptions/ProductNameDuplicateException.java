package com.example.ASWS.exceptions;

public class ProductNameDuplicateException extends RuntimeException {
    public ProductNameDuplicateException(String pName) {
      super("Product name already exists: " + pName);
    }
  }
