package com.mescobar.snack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mescobar.snack.model.security.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
