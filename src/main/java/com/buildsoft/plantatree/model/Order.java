package com.buildsoft.plantatree.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "orders")
public class Order {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "users_id")
    private User user;
	
	private String stripeToken;
	
	private Long[] cartItemsIds;
	
	private Long price;
	
	private String address;
	
	private String sortCode;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSortCode() {
		return sortCode;
	}

	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Order() {}
	
	public Order(User user, String stripeToken, Long[] cartItemsIds, Long price, String address, String sortCode) {
		super();
		this.user = user;
		this.stripeToken = stripeToken;
		this.cartItemsIds = cartItemsIds;
		this.price = price;
		this.address = address;
		this.sortCode = sortCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStripeToken() {
		return stripeToken;
	}

	public void setStripeToken(String stripeToken) {
		this.stripeToken = stripeToken;
	}

	public Long[] getCartItemsIds() {
		return cartItemsIds;
	}

	public void setCartItemsIds(Long[] cartItemsIds) {
		this.cartItemsIds = cartItemsIds;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;
}
