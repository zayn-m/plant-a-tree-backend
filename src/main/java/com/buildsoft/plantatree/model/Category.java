package com.buildsoft.plantatree.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="categories")
public class Category {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=3, max = 50)
    private String name;
    
    private String description;
    
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;
    
    public Category() {}
    
    public Category(String name, String description) {
    	this.name = name;
    	this.description = description;
    }
    
    public Long getId() {
    	return id;
    }
    
    public String getName() {
    	return name;
    }
    
    public String getDescription() {
    	return description;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
    	this.name = name;
    }
    
    public void setDescription(String description) {
    	this.description = description;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
