package com.example.ASWS.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// Product Class
@Entity
class Product {
	// Attributes of class fields (Parameters) are declared.
	private String productCategory;
	private String name;
	private float price;
	private int stockQuantity;
	
	// Default Constructor
	Product() {}
	
	// Parameterized Constructor
	Product(String productCategory, String name, float price, int stockQuantity) {
		this.productCategory = productCategory;
		this.name = name;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}
	  
	// Accessor Methods
	  
	public String getProductCategory() {
		return this.productCategory;
	}
	  
	public String getName() {
		return this.name;
	}
	  
	public float getPrice() {
		return this.price;
	}
	
	public int getStockQuantity() {
		return this.stockQuantity;
	}
	  
	// Mutator Methods
	  
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	  
	public void setName(String name) {
		this.name = name;
	}
	  
	public void setPrice(float price) {
		 this.price = price;
	}
	
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
		
	// Override Methods
		
	@Override
	public java.lang.String toString() {
		return "Product{" +
	                "Product Category=" + productCategory +
	                ", Name='" + name + '\'' +
	                ", Price=" + price + ", Stock Quantity=" + stockQuantity +
	                '}';
	}
	    
	@Override
	public int hashCode() {
		return java.util.Objects.hash(super.hashCode(), productCategory, name, price, stockQuantity);
	}	
}