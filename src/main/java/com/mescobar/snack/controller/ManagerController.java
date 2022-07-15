package com.mescobar.snack.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mescobar.snack.model.Product;
import com.mescobar.snack.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/manager")
@RequiredArgsConstructor
@Tag(name = "Controller for manager requests")
public class ManagerController {

	 private final ProductService productService;

	    @PostMapping
	    @Operation(summary = "Add new product to the database")
	    @ApiResponse(responseCode = "201", description = "Product successfully added to the database")
	    @ResponseStatus(code = HttpStatus.CREATED)
	    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
	    public void save(@RequestBody Product product) {
	        productService.save(product);
	    }
}
