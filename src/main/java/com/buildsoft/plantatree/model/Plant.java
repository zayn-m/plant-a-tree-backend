package com.buildsoft.plantatree.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "plants")
public class Plant {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
    @Size(min=3, max = 50)
    private String name;
    
	@Size(min=10, max = 500)
    private String description;
	
	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getStockLeft() {
		return stockLeft;
	}

	public void setStockLeft(Long stockLeft) {
		this.stockLeft = stockLeft;
	}

	private Long price;
	
	private Long stockLeft;

	@Transient
	private Long category_id;
	
	@Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

	@ManyToOne
    @JoinColumn(name = "categories_id")
    private Category category;
	
	private String imageUrl;
    
    public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Plant() {}
    
    public Plant(@NotBlank @Size(min = 3, max = 50) String name, String description, Category category, Long price, Long stockLeft, String imageUrl) {
		super();
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
		this.stockLeft = stockLeft;
		this.imageUrl = imageUrl;
	}

	public String getName() {
    	return name;
    }
    
    public String getDescription() {
    	return description;
    }
    
    public Category getCategory() {
    	return category;
    }
    
    public void setName(String name) {
    	this.name = name;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
}
