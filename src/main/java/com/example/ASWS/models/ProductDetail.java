package com.example.ASWS.models;

package com.example.ASWS.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// ProductDetail Class
@Entity
class ProductDetail {
	// Attributes of class fields (Parameters) are declared.
	private String description;
	private String comment;

	// Default Constructor
	ProductDetail() {}
	
	// Parameterized Constructor
	ProductDetail(String description, String comment) {
		this.description = description;
		this.comment = comment;
	}
	  
	// Accessor Methods
	  
	public String getDescription() {
		return this.description;
	}
	  
	public String getComment() {
		return this.comment;
	}
	  
	// Mutator Methods
	  
	public void setDescription(String description) {
		this.description = description;
	}
	  
	public void setComment(String comment) {
		this.comment = comment;
	}
		
	// Override Methods
		
	@Override
	public java.lang.String toString() {
		return "ProductDetail{" +
	                "Description=" + description +
	                ", Comment='" + comment + '\'' +
	                '}';
	}
	    
	@Override
	public int hashCode() {
		return java.util.Objects.hash(super.hashCode(), description, comment);
	}	
}