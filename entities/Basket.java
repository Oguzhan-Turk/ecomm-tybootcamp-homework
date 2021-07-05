package com.tybootcamp.ecomm.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer_basket")
public class Basket {

	@Id
	private Long id;

	@OneToMany
	private List<Product> items = new ArrayList<Product>();

	@OneToOne
	@MapsId
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public Basket() {
	}

	public void addProduct(Product product) {
		items.add(product);
	}

	public List<Product> getBasket() {
		return items;
	}

	public void setBasket(List<Product> basket) {
		this.items = basket;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

}
