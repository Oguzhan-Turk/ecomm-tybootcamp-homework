package com.tybootcamp.ecomm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tybootcamp.ecomm.enums.Gender;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Profile {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Column(name = "first_name")
	private String firstName;

	@NotNull
	@Column(name = "last_name")
	private String lastName;

	private String website;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	private String address;

	private String emailAddress;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	public Profile() {
	}

	public Profile(String firstName, String lastName, Gender gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
