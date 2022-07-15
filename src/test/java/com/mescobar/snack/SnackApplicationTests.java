package com.mescobar.snack;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import com.mescobar.snack.model.Product;
import com.mescobar.snack.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SnackApplicationTests {
	
	@Autowired
	protected TestRestTemplate testRestTemplate;

	private final String ADMIN_URL = "/api/v1/admin";
	private final String CLIENT_URL = "/api/v1/client";

	@Autowired
	protected ProductRepository productRepository;
	
	@BeforeEach
	public void cleanUp() {
	    productRepository.deleteAll();
	}
	 
	 @Test
	 public void adminPostProductSuccessTest() {
	     Product product = Product.builder()
	             .brand("Snaq Fabriq")
	             .category("Bar")
	             .name("Coco")
	             .flavour("Coconut")
	             .caloriesPer100Gram(407.0)
	             .weight(40.0)
	             .build(); 
	     
	     HttpEntity<Product> httpEntity = new HttpEntity<>(product);
	     ResponseEntity<Product> response = testRestTemplate.exchange(
	             ADMIN_URL, HttpMethod.POST, httpEntity, Product.class);

	     assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	 }
	 
	 @Test
	    public void clientGetProductsByBrandSuccessTest() {
	        // all products were in repository before calling endpoint
	        String targetBrand = "Snaq Fabriq";
	        Product productOne = Product.builder()
		             .brand(targetBrand)
		             .category("Bar")
		             .name("Coco")
		             .flavour("Coconut")
		             .caloriesPer100Gram(407.0)
		             .weight(40.0)
		             .build();  
	        		
	        Product productTwo = Product.builder()
		             .brand(targetBrand)
		             .category("Bar")
		             .name("Coco")
		             .flavour("Chocolate")
		             .caloriesPer100Gram(407.0)
		             .weight(40.0)
		             .build();  		
	        	
	        Product productTree = Product.builder()
		             .brand("Chicalab")
		             .category("Chocolate")
		             .name("Protein Milk Chocolate")
		             .flavour(null)
		             .caloriesPer100Gram(324.0)
		             .weight(120.0)
		             .build();  	
	        
	   
	        productRepository.saveAll(Arrays.asList(productOne, productTwo, productTree));


	        ResponseEntity<Product[]> response = testRestTemplate.getForEntity(
	                CLIENT_URL + "?brand=" + targetBrand, Product[].class);
			/*
			 * 
			 * ResponseEntity<Product[]> response = testRestTemplate
			 * .withBasicAuth(ADMIN_TEST_LOGIN, ADMIN_TEST_PASSWORD)
			 * .getForEntity(CLIENT_URL + "?brand=" + targetBrand, Product[].class);
			 */
	        assertEquals(response.getStatusCode(), HttpStatus.OK);
	        assertNotNull(response.getBody());
	        assertEquals(response.getBody().length, 2);
	    }
	 
	 @Test
	 public void clientGetProductsByBrandNotFoundTest() {
	     // there are no products in repository at all
	     ResponseEntity<Product[]> response = testRestTemplate.getForEntity(
	             CLIENT_URL + "?brand=" + "Chicalab", Product[].class);

	     assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
	 }
}
