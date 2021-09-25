package com.example.Products.services;
import java.util.List;

import com.example.Products.exceptions.*;
import com.example.Products.models.Product;
import com.example.Products.models.ProductDetail;
import com.example.Products.repositories.*;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
     
    private final ProductRepository repository;
    private final ProductDetailRepository productDetailRepository;

    ProductService(ProductRepository repository, ProductDetailRepository productDetailRepository) {
        this.repository = repository;
        this.productDetailRepository = productDetailRepository;
    }

    // add a product
    public String addProduct(Product product) { 
    int totalOccurances = 0; // there should only be 1 product with the name inputted above.
      for (Product p : getAllProducts()) {
          if (p.getName().equals(product.getName())) {
              totalOccurances++;
          }
      }
      if (totalOccurances > 0) {
          try {
            throw new ProductNameDuplicateException(product.getName());
          } catch (Exception e) {
            e.printStackTrace();
          }
      } else { // if the product name doesn't already exist
        repository.save(product); // save the product
        return product.toString(); // return the product
      } 

      // this will be returned if the product name already exists
      return "\nError, product name already exists: " + product.getName();
    }

    public Product getProduct(Long id) {   
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    // get a product by name
    public Product getProductByName(String productName) { 
      for (Product product : getAllProducts()) {
        if(product.getName().equals(productName)) {
            return product;
        } else {
            throw new ProductNotFoundException(productName);
          }
      } 
      return null; 
  }

    public List<Product> getAllProducts() {   
        return repository.findAll();
    }

    // check inventory for stock
    public float checkInventory(String productName, int quantity) {
        // we can recall getProductByName methd to reduce this but wasn't done to show logic
      for (Product product : getAllProducts()) {
        if(product.getName().equals(productName)) {
          if (product.getStockQuantity() >= quantity) {
            return product.getPrice();
          } else {
            return -1f; 
          }
        }
      }
      return -2f; // dummy value
         
    }

    // update product quantity
    public String updateProductQuantity(String pName, int quantity) {
        for (Product product : getAllProducts()) { // get product by name can be used here
            if(product.getName().equals(pName)) {
                Long id = product.getId();
                int currentQuantity = product.getStockQuantity();
                product.setStockQuantity(currentQuantity - quantity);

                updateProduct(product, id); // update product based on the products id 
                return "Quantity Updated Successfully.";
            }
        }
        return "Error."; // dummy value, an error is not possible in this contexts.
    }

    // update product
    public String updateProduct(Product newProduct, Long id) { 
    Product prod = repository.findById(id)
      .map(product -> {
        product.setProductCategory(newProduct.getProductCategory());
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        product.setStockQuantity(newProduct.getStockQuantity());
        return product;
      })
      .orElseGet(() -> {
        newProduct.setId(id);
        return newProduct;
      });

      int totalOccurances = 0; // there should only be 1 product with the name inputted above.
      for (Product p : getAllProducts()) {
          if (p.getName().equals(prod.getName())) {
              totalOccurances++;
          }
      }
      if (totalOccurances > 1) { // if there are more than 1 occurances where the productname already exists
        try {
            throw new ProductNameDuplicateException(prod.getName());
          } catch (Exception e) {
            e.printStackTrace();
          }
      } else {
        repository.save(prod);
        return prod.toString();
      }

      // return an error
      return "\nError, product name already exists: " + prod.getName();
    }

    // update product productdetail
    public Product updateProductProductDetail(Long id, long productDetailid) {
        Product product = repository.findById(id).orElseThrow(RuntimeException::new);
        ProductDetail productDetail = productDetailRepository.findById(productDetailid).orElseThrow(RuntimeException::new);
        product.setProductDetail(productDetail);
        return repository.save(product);
    }

    public void deleteProduct(Long id) {   
        repository.deleteById(id);
    }


}
