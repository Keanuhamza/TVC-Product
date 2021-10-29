package com.example.ASWS.models;

import java.util.Objects;

import javax.persistence.*;

// Product Class
@Entity
public class Product {
	private @Id @GeneratedValue(generator = "productSeq") Long id;
	private String productCategory;
	private String name;
	private float price;
	private int stockQuantity;

	@OneToOne
  	private ProductDetail productDetail;
	
	// Default Constructor
	public Product() {}
	
	// Parameterized Constructor
	public Product(Long id, String productCategory, String name, float price, int stockQuantity) {
		this.id = id;
		this.productCategory = productCategory;
		this.name = name;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}

	public Product(Long id, String productCategory, String name, float price, int stockQuantity, ProductDetail productDetail) {
		this.id = id;
		this.productCategory = productCategory;
		this.name = name;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.productDetail = productDetail;
	}

	// Accessor Methods

	public Long getId() {
        return id;
    }
	  
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

	public ProductDetail getProductDetail() {
		return productDetail;
	}
	  
	// Mutator Methods

	public void setId(Long id) {
        this.id = id;
    }
	  
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

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}
		
	// Override Methods

	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		if (!super.equals(object)) return false;
		Product product = (Product) object;
		return Float.compare(product.price, price) == 0 && stockQuantity == product.stockQuantity && id.equals(product.id) && productCategory.equals(product.productCategory) && name.equals(product.name) && Objects.equals(productDetail, product.productDetail);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, productCategory, name, price, stockQuantity, productDetail);
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", productCategory='" + productCategory + '\'' +
				", name='" + name + '\'' +
				", price=" + price +
				", stockQuantity=" + stockQuantity +
				", productDetail=" + productDetail +
				'}';
	}
}