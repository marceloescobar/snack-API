package com.mescobar.snack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mescobar.snack.model.Product;
import com.mescobar.snack.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

    public Product save(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }
    
    public List<Product> getProductsByBrand(String brand) {
        List<Product> products = productRepository.getProductsByBrand(brand);
        if (products.size() == 0) {
            return null;
        }
        return products;
    }

}
