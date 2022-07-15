package com.mescobar.snack.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mescobar.snack.model.security.Role;
import com.mescobar.snack.model.security.User;
import com.mescobar.snack.repository.RoleRepository;
import com.mescobar.snack.repository.UserRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Controller for app user registration")
public class RegistrationController {

	private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    
    @PostMapping("/registration")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void register(@RequestBody User userDetails) {
        User user = new User(userDetails.getLogin(), passwordEncoder.encode(userDetails.getPassword()));
        Role role = roleRepository.findByName("USER");
        user.setRole(role);
        userRepository.save(user);
    }
}
