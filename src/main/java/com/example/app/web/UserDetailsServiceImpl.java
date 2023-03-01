package com.example.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.app.domain.AppUser;
import com.example.app.domain.AppUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final AppUserRepository appUserRepository;
	
	@Autowired
	public UserDetailsServiceImpl(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser currentAppUser = appUserRepository.findByUsername(username);
		
		UserDetails user = new User(
			username,
			currentAppUser.getPasswordHash(),
			AuthorityUtils.createAuthorityList(currentAppUser.getRole())
		);
	
		return user;
	}
	
}
