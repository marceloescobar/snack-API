package com.mescobar.snack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mescobar.snack.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> getProductsByBrand(String brand);
}
