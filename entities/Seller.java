package com.tybootcamp.ecomm.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sellers")
public class Seller {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String accountId;

	@OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
	private Profile profile;

	public Seller() {
	}

	public Seller(String accountId) {
		this.accountId = accountId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	@Override
	public String toString() {
		if (profile == null) {
			return super.toString();
		} else {
			return getProfile().getFirstName() + " " + getProfile().getLastName();
		}
	}
}
