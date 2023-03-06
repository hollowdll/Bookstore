package com.example.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.app.web.BookController;
import com.example.app.web.BookRestController;
import com.example.app.web.UserDetailsServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookstoreApplicationTests {

	@Autowired
	private BookController bookController;
	
	@Autowired
	private BookRestController bookRestController;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService; 
	
	@Test
	public void bookControllerLoads() {
		assertThat(bookController).isNotNull();
	}
	
	@Test
	public void bookRestControllerLoads() {
		assertThat(bookRestController).isNotNull();
	}
	
	@Test
	public void userDetailsServiceLoads() {
		assertThat(userDetailsService).isNotNull();
	}

}
