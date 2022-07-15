package com.mescobar.snack.config;

import javax.transaction.Transactional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.mescobar.snack.model.security.Role;
import com.mescobar.snack.model.security.User;
import com.mescobar.snack.repository.RoleRepository;
import com.mescobar.snack.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	  boolean alreadySetup = false;
	    private final UserRepository userRepository;
	    private final RoleRepository roleRepository;

	    
	    @Override
	    @Transactional
	    public void onApplicationEvent(ContextRefreshedEvent event) {
	        if (alreadySetup) {
	            return;
	        }
	        Role adminRole = roleRepository.findByName("ADMIN");
	        User admin = new User("admin", "$2a$10$F3tuRTfb7okluAALfPJZ2.R9xHqzHKdtrGsROIVe4qEzukW7I167m");
	        admin.setRole(adminRole);
	        userRepository.save(admin);

	        alreadySetup = true;
	    }
}
