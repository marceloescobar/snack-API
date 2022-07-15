package com.mescobar.snack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@Column(name = "brand", nullable = false)
	private String brand;
	@Column(name = "category", nullable = false)
	private String category;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "flavour")
	private String flavour;
	@Column(name = "calories_per_100_gram", nullable = false)
	private Double caloriesPer100Gram;
	@Column(name = "weight_gram", nullable = false)
	private Double weight;
}
