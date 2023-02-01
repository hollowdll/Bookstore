package com.example.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.app.domain.Book;
import com.example.app.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner studentDemo(BookRepository repository) {
		return (args) -> {
			log.info("Save a couple of books");
			repository.save(new Book("Test book", "Test author", "123-456-789", 1999, 40));
			repository.save(new Book("Test book 2", "Test author 2", "987-654-00", 2000, 35));
			repository.save(new Book("Scam book", "Scammer123", "123123-123", 2023, 666.666));
			
			log.info("Fetch all books with all data");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
			
			log.info("Fetch all books by id and title");
			for(Book book : repository.findAll()) {
				log.info("ID = " + book.getId() + ", Title = " + book.getTitle());
			}

		};
	}

}
