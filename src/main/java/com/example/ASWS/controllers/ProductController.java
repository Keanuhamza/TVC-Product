package com.example.ASWS.controllers;

import java.util.List;

import com.example.ASWS.models.*;
import com.example.ASWS.services.ProductService;

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
  String newProduct(@RequestBody Product newProduct) {
    return productService.addProduct(newProduct);
  }

  // Single item by product id
  @GetMapping("/product/{id}")
  Product one(@PathVariable Long id) {
    return productService.getProduct(id); 
  }

  // Single item by product name
  @GetMapping("/productN/{productName}")
  Product one(@PathVariable String productName) {
    return productService.getProductByName(productName); 
  }

  // checking for inventory spaces
  @GetMapping("/product/{pName}/quantity/{qty}")
  float checkInventory(@PathVariable String pName, @PathVariable int qty) {
    return productService.checkInventory(pName, qty); 
  } 

  @GetMapping("/product/{pName}/updateQuantity/{qty}")
  String updateProductQuantity(@PathVariable String pName, @PathVariable int qty) {
    return productService.updateProductQuantity(pName, qty);
  }

  @PutMapping("/product/{id}")
  String replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {
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
