package com.mescobar.snack.service.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mescobar.snack.model.security.CustomUserPrincipal;
import com.mescobar.snack.model.security.User;
import com.mescobar.snack.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	 private final UserRepository userRepository;
	 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User user = userRepository.findByLogin(username);
	       
		 if (user == null) {
	            throw new UsernameNotFoundException(username);
	        }
	        return new CustomUserPrincipal(user);
	}

}
