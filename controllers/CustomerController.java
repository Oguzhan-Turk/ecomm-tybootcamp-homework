package com.tybootcamp.ecomm.controllers;

import com.tybootcamp.ecomm.entities.Profile;
import com.tybootcamp.ecomm.entities.Customer;
import com.tybootcamp.ecomm.repositories.ProductJpaRepository;
import com.tybootcamp.ecomm.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
	@Autowired
	private CustomerRepository _customerRepository;
	
	@GetMapping(path = "/")
	public ResponseEntity<?> getCustomerById(@RequestParam(value = "id") long id) {
		try {
			Customer customer = _customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
			System.out.println("The customer with id " + id + " = " + customer.toString());
			return new ResponseEntity<>(customer, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>("There isn't any customer with this name.", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(path = "/")
	public ResponseEntity<Customer> addNewCustomer(@Valid @RequestBody Customer customer) {
		Customer customerEntity = new Customer(customer.getAccountId());      //**************get account ID koyduk*********************
		
		Profile profile = new Profile(customer.getProfile().getFirstName(), customer.getProfile().getLastName(),
				customer.getProfile().getGender());
		customerEntity.setProfile(profile);
		customerEntity.getProfile().setWebsite(customer.getProfile().getWebsite());
		customerEntity.getProfile().setAddress(customer.getProfile().getAddress());
		customerEntity.getProfile().setEmailAddress(customer.getProfile().getEmailAddress());
		customerEntity.getProfile().setBirthday(customer.getProfile().getBirthday());
		customerEntity = _customerRepository.save(customerEntity);
		return new ResponseEntity<>(customerEntity, HttpStatus.OK);
	}
	@PutMapping(path = "/")
	public ResponseEntity<String> updateCustomer(@Valid @RequestBody Customer customer) {
		Customer customerEntity = _customerRepository.findById(customer.getId()).orElse(null);
		if (customerEntity == null) {
			return new ResponseEntity<>("This customer doesn't exists.", HttpStatus.NOT_FOUND);
		}
		customerEntity.setAccountId(customer.getAccountId());
		customerEntity.getProfile().setFirstName(customer.getProfile().getFirstName());
		customerEntity.getProfile().setLastName(customer.getProfile().getLastName());
		customerEntity.getProfile().setWebsite(customer.getProfile().getWebsite());
		customerEntity.getProfile().setBirthday(customer.getProfile().getBirthday());
		customerEntity.getProfile().setAddress(customer.getProfile().getAddress());
		customerEntity.getProfile().setEmailAddress(customer.getProfile().getEmailAddress());
		customerEntity.getProfile().setGender(customer.getProfile().getGender());
		customerEntity = _customerRepository.save(customerEntity);
		System.out.println("__________________________________________________________________");
		System.out.println("The row of " + customerEntity.toString() + " updated");
		return new ResponseEntity<>("The customer updated", HttpStatus.OK);
	}
}
