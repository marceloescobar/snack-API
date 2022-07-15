package com.mescobar.snack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mescobar.snack.model.security.User;

public interface UserRepository extends JpaRepository<User, Long> {

	  User findByLogin(String login);
}
