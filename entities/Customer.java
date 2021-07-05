package com.tybootcamp.ecomm.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String accountId;

	@OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
	private Profile profile;

	@NotNull
	private String name;

	@OneToOne
	private Basket basket;
	
	public Customer(String accountId) {
		this.accountId = accountId;
	}



	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	public Customer() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		if(profile == null) {
			super.toString();
		}
		return "Customer [id=" + id + ", accountId=" + accountId + ", profile=" + profile + ", name=" + name + "]";
	}
}
