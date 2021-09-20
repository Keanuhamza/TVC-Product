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
public class ProductController {

  @Autowired
  private ProductService productService;

  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/products")
  List<Product> all() {
    return productService.getAllProducts();
  }
  // end::get-aggregate-root[]

  @PostMapping("/product")
  Product newProduct(@RequestBody Product newProduct) {
    return productService.addProduct(newProduct);
  }

  // Single item
  @GetMapping("/product/{id}")
  Product one(@PathVariable Long id) {
    return productService.getProduct(id); }

  @PutMapping("/product/{id}")
  Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {
    return productService.updateProduct(newProduct, id);
  }

  @PutMapping("/product/{id}/productdetail/{productdetailid}")
  Product updateProductProductDetail(@PathVariable Long id, @PathVariable Long productdetailid) {
    return productService.updateProductProductDetail(id, productdetailid);
  }

  @DeleteMapping("/product/{id}")
  void deleteProduct(@PathVariable("id") Long id) {
    productService.deleteProduct(id);
  }
}