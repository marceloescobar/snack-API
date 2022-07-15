package com.mescobar.snack.model.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomUserPrincipal implements UserDetails {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final User user;
	 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		  return Collections.singletonList(
	                new SimpleGrantedAuthority("ROLE_" + user.getRole().getName())
	        );
	}

	@Override
	public String getPassword() {
		 return user.getPassword();
	}

	@Override
	public String getUsername() {
		 return user.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		 return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
