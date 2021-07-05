package com.tybootcamp.ecomm;

import com.github.javafaker.Book;
import com.github.javafaker.Faker;
import com.tybootcamp.ecomm.enums.Gender;
import com.tybootcamp.ecomm.entities.Basket;
import com.tybootcamp.ecomm.entities.Category;
import com.tybootcamp.ecomm.entities.Customer;
import com.tybootcamp.ecomm.entities.Product;
import com.tybootcamp.ecomm.entities.Profile;
import com.tybootcamp.ecomm.entities.Seller;
import com.tybootcamp.ecomm.repositories.CategoryRepository;
import com.tybootcamp.ecomm.repositories.CustomerRepository;
import com.tybootcamp.ecomm.repositories.ProductJpaRepository;
import com.tybootcamp.ecomm.repositories.ProfileRepository;
import com.tybootcamp.ecomm.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@EnableJpaRepositories(basePackages = "com.tybootcamp.ecomm.repositories")
@SpringBootApplication
public class EcommApplication implements CommandLineRunner {

	private List<Product> products = new ArrayList<>();
	private Set<Category> categories = new HashSet<Category>();

	private final CategoryRepository _categoryRepository;
	private final ProductJpaRepository _productJpaRepository;
	private final SellerRepository _sellerRepository;
	private final ProfileRepository _profileRepository;
	private final CustomerRepository _customerRepository;

	public EcommApplication(CustomerRepository _customerRepository, ProfileRepository _profileRepository,
			CategoryRepository _categoryRepository, ProductJpaRepository _productJpaRepository,
			SellerRepository _sellerRepository) {
		this._categoryRepository = _categoryRepository;
		this._productJpaRepository = _productJpaRepository;
		this._sellerRepository = _sellerRepository;
		this._profileRepository = _profileRepository;
		this._customerRepository = _customerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(EcommApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		Profile profile = new Profile("Oguz", "Turk", Gender.Male);
		profile = _profileRepository.save(profile);

		profile = _profileRepository.findById(1L).get();
		System.out.println(profile.getFirstName());
		
		Seller seller = new Seller("AID0001");
		seller.setProfile(profile);

		seller = _sellerRepository.save(seller);

		Profile ikramProfile = new Profile("Ikram", "Dagci", Gender.Male);
		ikramProfile.setBirthday(Date.valueOf("1996-3-8"));
		ikramProfile.setEmailAddress("ikram@example.com");
		ikramProfile.setWebsite("github.com/ikramgaci");
		ikramProfile.setAddress("Bayramoglu,KOCAELI");
		ikramProfile = _profileRepository.save(ikramProfile);

		ikramProfile = _profileRepository.findById(3L).get();

		Customer customerIkram = new Customer();
		customerIkram.setProfile(ikramProfile);
		customerIkram.setName("ikram.dagci");

		customerIkram = _customerRepository.save(customerIkram);


		initializeCategories();
		initializeSomeProducts();



	}

	private void initializeSomeProducts() {
		Product product = new Product();
		product.setSeller(_sellerRepository.findById(2L).get());
		product.setDescription("Some product");
		product.setFallIntoCategories((HashSet<Category>) categories.stream().filter(x -> x.getName().equals("Shoe"))
				.collect(Collectors.toSet()));
		_productJpaRepository.save(product);

		product = new Product();
		product.setSeller(_sellerRepository.getById(2L));
		product.setDescription("Some product 1");
		product.setFallIntoCategories((HashSet<Category>) categories.stream().filter(x -> x.getName().equals("Watch"))
				.collect(Collectors.toSet()));
		_productJpaRepository.save(product);

		product = new Product();
		product.setSeller(_sellerRepository.getById(2L));
		product.setDescription("Some product 2");
		product.setFallIntoCategories((HashSet<Category>) categories.stream().filter(x -> x.getName().equals("Casual"))
				.collect(Collectors.toSet()));
		_productJpaRepository.save(product);

		product = new Product();
		product.setSeller(_sellerRepository.findById(2L).get());
		product.setDescription("Some product 3");
		product.setFallIntoCategories((HashSet<Category>) categories.stream().filter(x -> x.getName().equals("Pant"))
				.collect(Collectors.toSet()));
		_productJpaRepository.save(product);

	}

	private void initializeCategories() {
		String[] categoryNames = { "Shoes", "Pant", "Skirt", "Casual", "Watch" };
		Category category = null;
		for (String catogoryName : categoryNames) {
			category = new Category(catogoryName);
			categories.add(category);
			_categoryRepository.save(category);
		}

	}

}
