package com.example.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.app.domain.AppUser;
import com.example.app.domain.AppUserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AppUserRepositoryTest {
	
	@Autowired
	private AppUserRepository appUserRepository;

	@Test
	public void findUserByUsername() {
		String username = "admin";
		AppUser user = appUserRepository.findByUsername(username);
		
		assertThat(user).isNotNull();
		assertThat(user.getUsername()).isEqualTo(username);
	}
	
	@Test
	public void createUser() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
		String plainTextPassword = "test.user";
		String hashedPassword = passwordEncoder.encode(plainTextPassword);
		
		AppUser user = new AppUser(
			"test.user",
			hashedPassword,
			"test.user@user.com",
			"USER"
		);
		
		appUserRepository.save(user);
		assertThat(user.getId()).isNotNull();
		assertThat(appUserRepository.findById(user.getId())).isNotEmpty();
		assertThat(user.getPasswordHash()).isNotEqualTo(plainTextPassword);
	}
	
	@Test
	public void deleteUser() {
		String username = "admin";
		AppUser user = appUserRepository.findByUsername(username);
		assertThat(user).isNotNull();
		appUserRepository.delete(user);
		
		AppUser userAfterDeletion = appUserRepository.findByUsername(username);
		assertThat(userAfterDeletion).isNull();
	}
	
}
