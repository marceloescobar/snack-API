package com.mescobar.snack.model.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "app_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@Column(name = "login", nullable = false)
	private String login;
	@Column(name = "password", nullable = false)
	private String password;

	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;

	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public User(String login, String password, Role role) {
		this.login = login;
		this.password = password;
		this.role = role;
	}
}
